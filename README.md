# springApi
siri springApi Test

- 사전과제 1 : 지자체 협약  api

- 개발 프레임워크
: springboot 2.2.1 , jdk1.8 , lombok, com.h2database:h2 , opencsv:5.0 

- 개발툴
: eclipse Jee
- build
: buildship gradle 3.x 

- 단위 테스트
: JUnit4 + postman 이용하여 테스트

- 실행 방법
: SpringApiApplication.java 를 run 한 다음, 단위테스트 수행.
: postman에서 api 호출
: http://localhost:8080/h2-console/login.jsp 에 접속하여 데이터 확인 (jdbc:h2:mem:testdb)

- 문제해결 방법
 : 문제해결을 못하겠어요..

  엔티티 설계:
  - 지자체 엔티티 : 지자체코드(pk) , 지자체명
  - 지자체 협약 정보 엔티티: id(pk), 지자체코드(fk), 지원대상, 용도, 
                            지원한도 - raw,     ((파생)) 지원한도금액, 
                            이차보전비율 - raw, ((파생)) 이차보전최소비율, 이차보전최대비율, 이차보전평균비율,
                            추천기관, 관리점, 취급점

  manytoone, onetomany 엔티티에 insert 어떻게 한꺼번에 수행하는지 모르겠음.
  배치로 (csv->tmp_table)
         (tmp_table->지자체)
         (tmp_table->지자체협약정보) 로 넣어야 하나 싶음

   - csv 파일 읽어와 raw데이터 한개의 table에 저장 후 읽어서 json 출력 하는 것 까지는 성공.


  springboot jpa spring 다 처음이라 잘 모르겠어요...

