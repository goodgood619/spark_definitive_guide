### spark_definitive_guide

--- 

관련 테스트 데이터는 https://github.com/goodgood619/Spark-The-Definitive-Guide 에서 clone을 하시면 됩니다.

<br/><br/>

관련 유의 사항으로는 data들의 위치 (파일 불러오기)를 신경써서 코딩을 하시기 바랍니다.


- Chapter2
    - DataFrame
        - 테이블의 데이터를 로우와 컬럼으로 단순하게 표현
        - 여러 컴퓨터에 데이터를 분산 저장함
            - 단일 컴퓨터에 저장하기에 데이터가 너무 크거나
            - 계산하는데 너무 오래 걸리기 때문
    - Partition
        - 모든 Executor가 병렬로 작업을 수행할 수 있게 분할하는 단위
        - Partition과 Executor의 최소 갯수에 맞게 병렬성이 조정 되는듯
    - 데이터 구조는 불변성
        - 그러면 변경을 해야 할때는?
            - Transformation
                - 좁은 의존성
                    - ex) where 구문
                        - val disisBy2 = myRange.where(“number % 2 = 0”)
                - 넓은 의존성
    - 지연 연산
        - Spark가 연산 그래프를 처리하기 직전까지 기다리는 동작 방식을 의미
            - Transformation의 실행 계획을 먼저 만듬
            - 최적화를 함
    - Spark UI
        - 기본 Port : 4040
            - localhost:4040
    - Function
        - show는 결과를 보여주는 것 (SQL 형태로)
        - explain은 실제 Debug처럼 자세한 상황을 보여줌

- Chapter 3
    - Dataset
        - java와 Scala의 정적 데이터 타입에 맞게 고안된 Spark의 구조적 API
            - 동적 타입언어인 Python과 R에선 사용 불가능
- Chapter 4
    - DataFrame
        - Schema에 명시된 데이터 타입의 일치 여부를 Runtime에 실행
        - Row 타입으로 구성된 Dataset
        - Garbage collection 및 객체 초기화 부하 가 있는 JVM 데이터 타입 대신, 자체 데이터 포맷을 사용해서 효율적인 연산이 가능
    - Dataset
        - Schema에 명시된 데이터 타입의 일치 여부를 Compiletime에 실행
    - Row
        - Data Record
        - DataFrame의 Record Type
    - 구조적 API 실행 과정
        - DataFrame/Dataset/SQL 이용해 코드 작성
        - 정상적인 코드면 Spark가 논리적인 실행 계획으로 변환함
        - Spark가 논리적 -> 물리적 실행 계획으로 변환 한 후 , 추가 최적화를 할 수 있는지 체크
        - Spark는 Cluster에서 물리적 실행 계획(RDD 처리)를 실행함
