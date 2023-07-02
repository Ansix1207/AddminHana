create or replace PROCEDURE deposit(
    p_acc_id IN NUMBER,
    p_amount IN NUMBER,
    p_description IN VARCHAR2,
    p_result OUT NUMBER,
        p_id OUT NUMBER
)
IS
    v_acc_amount NUMBER := 0;
    v_old_balance NUMBER;
    v_new_balance NUMBER;
    v_error_message VARCHAR2(100);
BEGIN
    -- account 테이블에서 acc_amount 값을 가져옴
SELECT acc_balance INTO v_acc_amount FROM account WHERE acc_id = p_acc_id and acc_isactive = 'Y' FOR UPDATE WAIT 2;
-- 가져온 값에 입력받은 p_amount를 더함
v_old_balance := v_acc_amount;
    v_new_balance := v_acc_amount + p_amount;
    -- 업데이트 수행
UPDATE account SET acc_balance = v_new_balance WHERE acc_id = p_acc_id;
--id, 계좌번호, 상대계좌번호,
INSERT INTO "TRANSACTION" VALUES (transaction_seq.nextval,p_acc_id, null, '+', SYSTIMESTAMP,p_amount,p_description, 'T',v_new_balance);
p_id := transaction_seq.CURRVAL;
    -- 성공 시 1, 실패 시 0 반환
    p_result := 1;
        -- 계좌번호, 성공 여부, 입금액(amount)을 출력
    DBMS_OUTPUT.PUT_LINE('계좌번호: ' || p_acc_id);
    DBMS_OUTPUT.PUT_LINE('성공 여부: ' || p_result);
    DBMS_OUTPUT.PUT_LINE('입금액: ' || p_amount);
    DBMS_OUTPUT.PUT_LINE('입금 전 금액: ' || v_old_balance);
    DBMS_OUTPUT.PUT_LINE('입금 후 금액: ' || v_new_balance);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- account_id에 해당하는 데이터가 없는 경우 예외 처리
        DBMS_OUTPUT.PUT_LINE('해당 계좌가 존재하지 않습니다.');
ROLLBACK;
v_new_balance:= 0;
INSERT INTO "TRANSACTION" VALUES (transaction_seq.nextval,p_acc_id, null, '+', SYSTIMESTAMP,p_amount,'계좌가 존재하지 않습니다.', 'F',v_new_balance);
p_result := 2;
        p_id := transaction_seq.CURRVAL;
COMMIT;

WHEN OTHERS THEN
        -- 그 외 예외 처리
        DBMS_OUTPUT.PUT_LINE('에러 발생: ' || SQLERRM);
ROLLBACK; -- 롤백 (변경 내용 취소)
v_error_message := SQLERRM;
        v_new_balance:= 0;
        p_result := 0;
INSERT INTO "TRANSACTION" VALUES (transaction_seq.nextval,p_acc_id, null, '+', SYSTIMESTAMP,p_amount,v_error_message, 'F',v_new_balance);
p_id := transaction_seq.CURRVAL;
COMMIT;
END;
