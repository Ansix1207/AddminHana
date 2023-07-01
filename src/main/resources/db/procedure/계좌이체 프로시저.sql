create or replace PROCEDURE transfer(
    p_acc_id IN NUMBER,
    p_counter_acc_id IN NUMBER,
    p_amount IN NUMBER,
    p_description IN VARCHAR2,
    p_result OUT NUMBER,
    pw_id OUT NUMBER
)
IS
    vw_acc_balance NUMBER; -- acc_balance from ACCOUNT TABLE
    vw_old_balance NUMBER; -- v_old_balance = v_acc_balance
    vw_new_balance NUMBER; -- v_new_balance = p_amount + v_old_balance
    vw_error_message VARCHAR2(100);
    vw_result NUMBER;
    vd_acc_balance NUMBER; -- acc_balance from ACCOUNT TABLE
    vd_old_balance NUMBER; -- v_old_balance = v_acc_balance
    vd_new_balance NUMBER; -- v_new_balance = p_amount + v_old_balance
    vd_error_message VARCHAR2(100);
    vd_result NUMBER;
    v_error_message VARCHAR2(100);

BEGIN
    -- 트랜잭션 시작
BEGIN
        -- 출금 로직 시작 (withdraw)
        vw_result :=-1; -- (-1)출금 계좌가 없음
SELECT acc_balance INTO vw_acc_balance FROM account WHERE acc_id = p_acc_id and acc_isactive ='Y' FOR UPDATE WAIT 2;

IF p_amount <= vw_acc_balance THEN
            --가져온 값에서 출금액을 차감
            vw_old_balance := vw_acc_balance;
            vw_new_balance := vw_old_balance - p_amount;
        -- 업데이트 수행
UPDATE ACCOUNT SET acc_balance = vw_new_balance WHERE acc_id = p_acc_id and acc_isactive ='Y';
vw_result := 1;

ELSE
            p_result :=-2; -- (-2)출금 잔고 부족
INSERT INTO "TRANSACTION" VALUES (transaction_seq.nextval,p_acc_id, p_counter_acc_id, '-', SYSTIMESTAMP,p_amount,'계좌 잔고 부족', 'F',vw_acc_balance);
pw_id := transaction_seq.CURRVAL;
            RETURN;
END IF;
        --출금 로직 끝 (withdraw)

        -- 출금 프로시저 호출하여 p_acc_id에서 p_amount만큼 출금

        -- 입금 프로시저 호출하여 p_counter_acc_id에 p_amount만큼 입금
        vd_result :=-3; -- (-3)입금 계좌가 없음
SELECT acc_balance INTO vd_acc_balance FROM account WHERE acc_id = p_counter_acc_id and acc_isactive ='Y' FOR UPDATE WAIT 2;
-- 가져온 값에 입력받은 p_amount를 더함
vd_old_balance := vd_acc_balance;
        vd_new_balance := vd_acc_balance + p_amount;
        -- 업데이트 수행
UPDATE account SET acc_balance = vd_new_balance WHERE acc_id = p_counter_acc_id and acc_isactive ='Y';
--id, 계좌번호, 상대계좌번호,
INSERT INTO "TRANSACTION" VALUES (transaction_seq.nextval,p_acc_id, p_counter_acc_id, '-', SYSTIMESTAMP,p_amount,p_description, 'T',vw_new_balance);
pw_id := transaction_seq.CURRVAL;
INSERT INTO "TRANSACTION" VALUES (transaction_seq.nextval,p_counter_acc_id, p_acc_id, '+', SYSTIMESTAMP,p_amount,p_description, 'T',vd_new_balance);
vd_result :=1; -- (1)입금 성공
        IF vw_result =1  and vd_result =1 THEN
            p_result :=1;
        ELSIF vw_result <> 1 THEN
            p_result := -1;
        ELSIF vd_result <>1 THEN
            p_result := -3;
END IF;

        DBMS_OUTPUT.PUT_LINE('계좌 이체 성공:');
        DBMS_OUTPUT.PUT_LINE('  - 계좌번호: ' || p_acc_id || ', 차감액: ' || p_amount);
        DBMS_OUTPUT.PUT_LINE('  - 상대 계좌번호: ' || p_counter_acc_id || ', 추가액: ' || p_amount);
EXCEPTION
        WHEN NO_DATA_FOUND THEN
        -- account_id에 해당하는 데이터가 없는 경우 예외 처리
            DBMS_OUTPUT.PUT_LINE('해당 계좌가 존재하지 않습니다.');
ROLLBACK;
IF vw_result = -1 THEN --출금 계좌가 존재 x
                INSERT INTO "TRANSACTION" VALUES (transaction_seq.nextval,p_acc_id, p_counter_acc_id, '-', SYSTIMESTAMP,p_amount,'출금 계좌가 존재하지 않습니다.', 'F',0);
                p_result := -1;
            ELSIF vd_result =-3 THEN
                INSERT INTO "TRANSACTION" VALUES (transaction_seq.nextval,p_acc_id, p_counter_acc_id, '-', SYSTIMESTAMP,p_amount,'입금 계좌가 존재하지 않습니다.', 'F',0);
               -- p_result := vd_result;
               p_result := vd_result;

END IF;
            pw_id := transaction_seq.CURRVAL;
COMMIT;
WHEN OTHERS THEN
            -- 예외 처리
            p_result := 0;
ROLLBACK; -- 롤백 (변경 내용 취소)
v_error_message := SQLERRM;
INSERT INTO "TRANSACTION" VALUES (transaction_seq.nextval,p_acc_id, p_counter_acc_id, '-', SYSTIMESTAMP,p_amount,v_error_message, 'F',0);
pw_id := transaction_seq.CURRVAL;
COMMIT;
DBMS_OUTPUT.PUT_LINE('에러 발생: ' || SQLERRM);

END;
EXCEPTION
    WHEN OTHERS THEN
        -- 예외 처리
        p_result := 0;
        DBMS_OUTPUT.PUT_LINE('에러 발생: ' || SQLERRM);
END;
