# 구글 클라우드를 이용한 CentOS8에서 MySql 설정

사전에 우리가 사용할 포트 (mySql에서 사용하는 3306 포트, tomcat에서 사용하는 8080 포트)는 열어두고 작업을 시작했다.  
우리는 사용자 계정으로 하고 있기 때문에, superuser의 힘을 빌리기(?) 위해 실행 코드 앞에 `sudo`를 붙인다

## 1. 설치
```
    sudo yum install mysql-server
```

## 2. 재시작시 Mysql 시작하도록 설정
```
    sudo systemctl enable mysqld
```

## 3. mySql 실행
```
    sudo systemctl start mysqld
```

## 4. mysql 설정하기 (보안설정)
```
    mysql_secure_installation
```
`mysql_secure_installation`을 검색하면 각 옵션에 대한 설명이 나온다.  
우선은 해당 보안 설정에서 안내하는대로 계속 실행했다.  
(순서대로 비밀번호 설정 -> 익명 사용자 삭제 -> root의 원격 접속 제한 -> test DB 삭제 -> privileges 테이블 재시작 여부)

## 5. mysql root 계정으로 접속
```
    mysql -u root -p
```
일단 배우는 과정 상에서는 `12345678`로 설정했음

## 5-1. 유저 생성 (mysql 접속 후 실행)
```sql
    /*mysql은 외부/내부에 대한 권한이 나뉘어져 있으므로 */
    /* 둘 다 등록해줍시다 */
    /* 로컬 접속에 대한 유저 설정 */
    create user 'the'@'localhost' identified by '12345678';
    /* 외부 접속에 대한 유저 설정 */
    create user 'the'@'%' identified by '12345678';
    /* 여기서 '%'는 모든 아이피를 의미함 */
```

## 5-2. 권한 부여
```sql
    grant all privileges on *.* to 'the'@'%';
    /* 'all privileges'은 모든 권한을 의미 */
    /* *.*은 모든 스키마(*) 의(.) 모든 테이블(*)을 의미함 */
    /* 일단 모든 권한을 부여하기로 했습니다 */
```
위에서는 모든 권한을 부여했지만,, 일부만 부여할 수도 있습니다..
```sql
    /* 예시 */
    grant select, insert, update on *.* to 'user'@'%';
```

## 프로젝트에서 mySQL 설정
```s
#datasource 설정
spring.datasource.url=jdbc:mysql:@(GCP VM 외부 IP):3306:the
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=the
spring.datasource.password=12345678

spring.jpa.open-in-view=false
#jpa-common
spring.jpa.show-sql=true


# generate-ddl 적용시에 주의 (개발환경에서만)
spring.jpa.generate-ddl=false
spring.jpa.hibernate.use-new-id-generator-mappings=true

#jpa에 대해 mysql을 사용하도록 DB 설정
spring.jpa.database=mysql
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect

```
