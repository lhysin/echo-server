# Echo Server

## 개요
이 프로젝트는 Spring Boot를 사용하여 간단한 Echo 서버를 구현한 애플리케이션입니다. 클라이언트 요청을 처리하고, 요청 URI를 반환하거나 특정 기능을 수행하는 REST API를 제공합니다.

## 주요 기능
1. **애플리케이션 이름 반환**  
   `/app-name` 엔드포인트를 호출하면 애플리케이션 이름을 반환합니다.

2. **고비용 계산 시뮬레이션**  
   `/high-calculation` 엔드포인트를 호출하면 랜덤한 시간 동안 대기한 후 계산 시간을 반환합니다.

3. **Fallback 처리**  
   정의되지 않은 모든 요청에 대해 요청 URI를 반환하는 fallback 엔드포인트를 제공합니다.

## 기술 스택
- **언어**: Java, Kotlin
- **프레임워크**: Spring Boot
- **빌드 도구**: Gradle

## 실행 방법

1. 프로젝트를 클론합니다:
   ```bash
   git clone -b master https://github.com/lhysin/echo-server.git
   ```

2. Gradle을 사용하여 애플리케이션을 빌드하고 실행합니다:
   ```bash
   ./gradlew bootRun
   ```

3. 서버가 실행되면 기본 포트(http://localhost:8080)에서 API를 호출할 수 있습니다.

## API 엔드포인트
- **GET /app-name**  
  애플리케이션 이름을 반환합니다.

- **GET /high-calculation**  
  고비용 계산을 시뮬레이션하고 소요 시간을 반환합니다.

- **GET /**  
  요청 URI를 반환하는 fallback 엔드포인트입니다.

## Docker 생성 방법

* 스크립트를 사용하여 도커 이미지 생성:
   ```bash
   ./create-docker-image.sh
   ```

## Docker 실행 방법

* Docker 이미지를 실행합니다:
   ```bash
   docker run -p 8080:8080 -e SPRING_APPLICATION_NAME=this_is_local_server lhysin/echo-server
   ```



### 주요 기능
- Gradle을 사용하여 애플리케이션을 빌드합니다.
- Docker Hub에 로그인하여 이미지를 푸시합니다.
- 멀티 플랫폼(linux/amd64, linux/arm64) 이미지를 빌드하고 Docker Hub에 업로드합니다.

### 생성된 이미지
- Docker Hub에 푸시된 이미지 이름: `lhysin/echo-server:latest`

## 라이센스
이 프로젝트는 MIT 라이센스를 따릅니다.
