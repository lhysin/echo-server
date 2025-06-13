#!/bin/bash

# 설정 변수
DOCKERHUB_USERNAME="lhysin"
DOCKER_IMAGE_NAME="echo-server"
DOCKER_TAG="latest"
TIMEZONE="Asia/Seoul"

echo "▶️ Gradle build 시작"
./gradlew clean bootJar || { echo "Gradle build 실패"; exit 1; }

# Docker Hub 로그인
echo "▶️ Docker Hub 로그인"
docker login -u $DOCKERHUB_USERNAME || { echo "Docker Hub 로그인 실패"; exit 1; }

# 멀티 플랫폼 빌드를 위한 buildx 빌더 인스턴스 생성 (존재하지 않으면)
BUILDER_NAME="multi-builder"

if ! docker buildx inspect $BUILDER_NAME &>/dev/null; then
  echo "▶️ Buildx 빌더 생성: $BUILDER_NAME"
  docker buildx create --name $BUILDER_NAME --use
else
  echo "▶️ 기존 빌더 사용: $BUILDER_NAME"
  docker buildx use $BUILDER_NAME
fi

# 플랫폼 지정
PLATFORMS="linux/amd64,linux/arm64"

FULL_IMAGE_NAME="$DOCKERHUB_USERNAME/$DOCKER_IMAGE_NAME:$DOCKER_TAG"

echo "▶️ 멀티 플랫폼용 Docker 이미지 빌드 및 푸시: $FULL_IMAGE_NAME"
docker buildx build \
  --platform $PLATFORMS \
  -t $FULL_IMAGE_NAME \
  --push . || { echo "Docker buildx 빌드 실패"; exit 1; }

echo "✅ 완료: 멀티 플랫폼 이미지가 Docker Hub에 푸시되었습니다 -> $FULL_IMAGE_NAME"
