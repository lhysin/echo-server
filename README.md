# Echo Server

## 개요
이 프로젝트는 Spring Boot를 사용하여 간단한 Echo 서버를 구현한 애플리케이션입니다. 클라이언트 요청을 처리하고, 요청 URI를 반환하거나 특정 기능을 수행하는 REST API를 제공합니다.

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
./gradlew bootRun
3. 서버가 실행되면 기본 포트(예: http://localhost:8080)에서 API를 호출할 수 있습니다.

### Docker로 실행하기

1. jar 파일을 빌드합니다:
   ```bash
   ./gradlew clean build
   ```
2. Docker 이미지를 빌드합니다:
   ```bash
   docker build -t echo-server .
   ```
3. 컨테이너를 실행합니다:
   ```bash
   docker run -p 8080:8080 echo-server
   ```

## 라이센스
* 이 프로젝트는 MIT 라이센스를 따릅니다.
