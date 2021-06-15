# CentOS8 (CUI 환경)에서 MySql 초기 설치, 설정

우리는 사용자 계정으로 하고 있기 때문에, superuser의 힘을 빌리기(?) 위해 실행 코드 앞에 `sudo`를 붙인다

## 1. 설치
```
    sudo yum install mysql-server
```

## 2. 재시작시 MySQL 시작하도록 설정
```
    sudo systemctl enable mysqld
```

## 3. MySQL 실행
```
    sudo systemctl start mysqld
```

## 4. MySQL 설정하기 (보안설정)
```
    mysql_secure_installation
```
문자열 인코딩도 설정할 수 있지만, 일단 나중에 처리합니다.. <br>
여기서 비밀번호(12345678)도 설정했고, 이것저것 했습니다만 일단 모두 y 처리 했습니다. 추가 공부합시다.

## 5. MySQL root 계정으로 접속
```
    mysql -u root -p (비밀번호)
```
일단 배우는 과정 상에서는 `12345678`로 설정했음

## 5-1. 유저 생성 (MySQL 접속 후 실행)
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
