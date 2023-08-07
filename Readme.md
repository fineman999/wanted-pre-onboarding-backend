## 💛 원티드 프리온보딩 백엔드 인턴십 - 선발 과제


### **_📌_** 차례

---

1. [지원자의 성명](#지원자의-성명)
2. [애플리케이션의 환경 및 실행 방법](#애플리케이션의-환경-및-실행-방법)
2. [데이터베이스-테이블-구조](#데이터베이스-테이블-구조)
3. [구현한 API의 동작을 촬영한 데모 영상 링크](#구현한-API의-동작을-촬영한-데모-영상-링크)
4. [구현 방법 및 이유에 대한 간략한 설명](#구현-방법-및-이유에-대한-간략한-설명)
7. [API 명세](#API-명세)
8. [아키텍처 구조](#아키텍처-구조)


</br>

### 지원자의 성명

---

- 박찬흠


</br>

### 애플리케이션의 환경 및 실행 방법

---
#### 실행 환경
- java 17버전 이상
- spring boot 3.0.0 이상
- mysql 8.0.0 이상
- 

#### 실행 전 준비 사항

1. docker-compose 가 설치 되어 있는 상태
2. application-jwt.yml 과 application-mysql.yml 환경 변수 세팅

- 실행 - 리눅스 환경

```shell
$ ./gradlew build
$ docker-compose up -d
```

</br>


### 데이터베이스-테이블-구조

---

<p align="center">
  <img src="https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F53cc13bf-c643-47f6-86db-ed66617e76dd%2FUntitled.png?table=block&id=55f4d98d-624a-42f7-8f4d-8ea940b68079&spaceId=8f9374ea-d950-4add-878f-648f2923ed06&width=1920&userId=2f121881-2289-461c-a96c-58cde2646312&cache=v2" alt="HOT" width="250px" />
</p>

<br/>


<br/>

### 구현한 API의 동작을 촬영한 데모 영상 링크

---

[데모 영상 링크](https://drive.google.com/file/d/1uue6izkuWSlj2vFrcj4PVeTOAHwFSv7U/view?usp=sharing)

<br/>

### 구현 방법 및 이유에 대한 간략한 설명

---

#### 도메인과 영속성 객체(엔티티) 분리
- 도메인 객체는 POJO 또는 lombok을 제외한 나머지 라이브러리를 사용하지 않게 설계하였습니다.

#### Testability
- TEST 용이 가능하게 인터페이스 설계 및 의존성을 낮추는 방향으로 설계하였습니다.


### API 명세

[주소](http://13.125.156.78/swagger-ui/index.html#/)

---

### 아키텍처 구조
<p align="center">
    <img src="https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F69b4b7e3-dc9b-4f7c-a1cd-375921bb6a6e%2FUntitled.png?table=block&id=e31c3c7e-d80f-4c33-adbe-6451072eb1ab&spaceId=8f9374ea-d950-4add-878f-648f2923ed06&width=1920&userId=2f121881-2289-461c-a96c-58cde2646312&cache=v2" width="400px">
</p>
