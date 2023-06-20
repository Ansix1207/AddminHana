DROP TABLE PAYMENTHISTORY;

CREATE TABLE PAYMENTHISTORY (
    pay_id     NUMBER     NOT NULL,
    c_id       NUMBER     NOT NULL,
    pay_date   TIMESTAMP(0)     NOT NULL,
    pay_amount NUMBER DEFAULT 0 NOT NULL,
    pay_type   VARCHAR(15)     NOT NULL,
    CONSTRAINT PK_PAYMENTHISTORY PRIMARY KEY (pay_id),
    CONSTRAINT CHK_PAY_TYPE CHECK (pay_type IN ('쇼핑', '의료', '식비', '카페','반려동물','PX','배달','디지털'))
);

SELECT * FROM PAYMENTHISTORY;
