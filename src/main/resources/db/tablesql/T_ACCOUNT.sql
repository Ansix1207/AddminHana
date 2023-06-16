DROP TABLE ACCOUNT;

CREATE TABLE ACCOUNT (
acc_id NUMBER NOT NULL,
acc_cid NUMBER NOT NULL,
acc_date TIMESTAMP(0) NOT NULL,
acc_balance NUMBER DEFAULT 0 NOT NULL,
acc_password VARCHAR(255) NOT NULL,
acc_pid NUMBER NOT NULL,
acc_p_category VARCHAR(20) NOT NULL,
acc_pname VARCHAR(100) NULL,
acc_interestrate NUMBER(5,2) NOT NULL,
acc_collateralvalue NUMBER NULL,
acc_interest_day NUMBER NOT NULL,
acc_contract_month NUMBER NOT NULL,
acc_maturitydate TIMESTAMP(0) NOT NULL,
acc_isactive CHAR(1) DEFAULT 'Y' NOT NULL,
CONSTRAINT PK_ACCOUNT PRIMARY KEY (acc_id),
CONSTRAINT CK_ACCOUNT_ACC_ISACTIVE CHECK(acc_isactive IN('Y','N')),
-- INSERT or UPDATE 할때 CHECK 조건을 걸어서 acc_p_category IN () 구문 안에 있는 값들만 입력을 할 수 있도록 합니다.
CONSTRAINT CK_ACCOUNT_ACC_P_CATEGORY CHECK (acc_p_category IN ('보통예금', '정기예금', '정기적금', '자유적금','신용대출','담보대출'))
);

--편하게 보기 위해 Alias를 ("[column_ex] as [alias_ex] ") 사용한 select
SELECT acc_id as "계좌번호", acc_cid as "손님고유ID", acc_balance as "계좌 잔고",acc_password as "비밀번호", acc_pid as "상품번호", 
acc_p_category as "상품종류",acc_pname as "상품 이름", acc_interestrate as "이자율", acc_collateralvalue as "담보가액", 
acc_interest_day as "이자지급일", acc_contract_month as "상품약정일", acc_date as "상품 가입일  ",acc_maturitydate as "상품만기일", acc_isactive as "활성여부" FROM ACCOUNT;
