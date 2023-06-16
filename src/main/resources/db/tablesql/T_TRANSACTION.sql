DROP TABLE TRANSACTION;

CREATE TABLE TRANSACTION (
    t_id           NUMBER           NOT NULL,
    t_accid        NUMBER           NOT NULL,
    t_counterpart_id NUMBER         NULL,
    t_type         CHAR(1)          NOT NULL CHECK (t_type IN ('+', '-')),
    t_date         TIMESTAMP(0)     NOT NULL,
    t_amount       NUMBER           NOT NULL,
    t_description  VARCHAR(100)     NOT NULL, --입금, 출금, 대출,상환
    t_status       CHAR(1)          NOT NULL CHECK (t_status IN ('T', 'F')),
    t_balance      NUMBER           NOT NULL,
    CONSTRAINT PK_TRANSACTION PRIMARY KEY (t_id)
);