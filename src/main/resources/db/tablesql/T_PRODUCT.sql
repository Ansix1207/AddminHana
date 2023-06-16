-- 테이블 삭제
DROP TABLE PRODUCT;

-- 테이블 생성
CREATE TABLE PRODUCT (
    p_id NUMBER PRIMARY KEY NOT NULL,
    p_category VARCHAR(20) NOT NULL,
    p_name VARCHAR(100) NOT NULL,
    p_description VARCHAR(1000) NOT NULL,
    p_interestrate NUMBER(5,2) NOT NULL,
    p_interest_day NUMBER NOT NULL,
    p_date TIMESTAMP(0) NULL,
    p_contract_month NUMBER NOT NULL,
    p_isactive CHAR(1) NOT NULL,
    p_limit NUMBER NULL,
    p_collateralrate NUMBER(5,2) NULL,
    p_mincreditgrade NUMBER NULL,
    p_jobtype VARCHAR(20),
    CONSTRAINT CK_PRODUCT_P_ISACTIVE CHECK (p_isactive IN ('Y', 'N')),
    CONSTRAINT CK_PRODUCT_P_CATEGORY CHECK (p_category IN ('보통예금', '정기예금', '정기적금', '자유적금','신용대출','담보대출'))
);

