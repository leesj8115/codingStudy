# 스프링 스터디 - 1회

## 개요
[김영한의 스프링 입문](https://www.inflearn.com/course/스프링-입문-스프링부트)을 보고 정리하는 스터디

## 기간
2021-08.25-29 (5일간)

## 정리

### 실행했을 때 나오는 폴더 구조

![폴더구조 사진](https://user-images.githubusercontent.com/14111233/130898010-bd002b93-978a-4f42-b16a-502307c75d80.png) 

- gradle : 라이브러리와 버전을 관리하기 위한 도구
- src/main, src/test : 테스트 코드의 중요성이 크기 때문에, main과 별개의 폴더로 구조화 하고 있음.
- src/main/java, src/main/resources : 코드 파일과 그 외 필요한 파일을 나누어 관리할 수 있음 
- **build.gradle** : 해당 파일에 적혀있는 내용을 토대로 라이브러리를 관리한다.
  > `dependencies` 안에 라이브러리를 나열한다.  
  > `repositories` 에서 어디서 라이브러리를 가져올 것인지 설정한다.
- .gitignore : 깃 연동시에 업로드 하지 않을 파일들을 마스킹

### 라이브러리
- 스프링 부트에서 필요한 라이브러리를 추가하면, 그와 관련된 라이브러리까지 자동으로 연결되서 편리하다!
- `spring-boot-starter-web`은 `tomcat`과 `spring-webmvc`를 포함하고 있다.
- 로그 관리를 위해 `slf4j`와 `logback`을 사용한다. (기본으로 활용)
- 테스트를 위해서는 `Junit`을 사용한다.
- 처음 학습할 때는 매번 tomcat을 재부팅하는데, `spring-devtools` 라이브러리를 사용하면 매번 재실행할 필요가 없다!

### 빌드
윈도우에서는 해당 프로젝트 폴더에서 `gradlew build`를 실행한다. 이후 `(프로젝트폴더)/build/libs`안에 있는 `(프로젝트).jar`를 java를 통해 실행하여 서버를 운용할 수 있다. 내장되어있는 톰캣 덕분에, 이 파일을 통해서 바로 운용할 수 있다.

