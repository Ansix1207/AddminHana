create or replace PROCEDURE withdraw(
    p_acc_id IN NUMBER,
    p_amount IN NUMBER,
    p_description IN VARCHAR2,
    p_result OUT NUMBER,
    p_id OUT NUMBER
)
IS
    v_acc_balance NUMBER;
    v_old_balance NUMBER;
    v_new_balance NUMBER;
    v_error_message VARCHAR2(100);
BEGIN
    -- account 테이블에서 acc_amount 값을 가져옴
SELECT acc_balance INTO v_acc_balance FROM account WHERE acc_id = p_acc_id;

-- 출금액이 계좌의 잔액보다 작거나 같은지 확인
IF p_amount <= v_acc_balance THEN
        -- 가져온 값에서 출금액을 차감
        v_old_balance := v_acc_balance;
        v_new_balance := v_acc_balance - p_amount;

        -- 업데이트 수행
UPDATE account SET acc_balance = v_new_balance WHERE acc_id = p_acc_id;
INSERT INTO "TRANSACTION" VALUES (transaction_seq.nextval,p_acc_id, null, '-', SYSTIMESTAMP,p_amount,p_description, 'T',v_new_balance);
p_id := transaction_seq.CURRVAL;

        -- 커밋 (변경 내용을 영구적으로 저장)
        --COMMIT;

        -- 출금 성공 시 1 반환
        p_result := 1;

        -- 계좌번호, 성공 여부, 출금액, 출금 전 금액, 출금 후 금액 출력
        DBMS_OUTPUT.PUT_LINE('계좌번호: ' || p_acc_id);
        DBMS_OUTPUT.PUT_LINE('성공 여부: ' || p_result);
        DBMS_OUTPUT.PUT_LINE('출금액: ' || p_amount);
        DBMS_OUTPUT.PUT_LINE('출금 전 금액: ' || v_old_balance);
        DBMS_OUTPUT.PUT_LINE('출금 후 금액: ' || v_new_balance);
ELSE
        -- 출금액이 잔액보다 크거나 같으면 출금 실패
        p_result := -2;
        v_new_balance:= 0;
INSERT INTO "TRANSACTION" VALUES (transaction_seq.nextval,p_acc_id, null, '-', SYSTIMESTAMP,p_amount,'잔액 부족', 'F',v_new_balance);
p_id := transaction_seq.CURRVAL;
        -- 계좌번호, 성공 여부, 출금액 출력
        DBMS_OUTPUT.PUT_LINE('계좌번호: ' || p_acc_id);
        DBMS_OUTPUT.PUT_LINE('성공 여부: ' || p_result);
        DBMS_OUTPUT.PUT_LINE('출금액: ' || p_amount);
        DBMS_OUTPUT.PUT_LINE('잔액 부족: 출금액이 계좌 잔액보다 큽니다.');
END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- account_id에 해당하는 데이터가 없는 경우 예외 처리
        DBMS_OUTPUT.PUT_LINE('해당 계정이 존재하지 않습니다.');
          v_new_balance:= 0;
INSERT INTO "TRANSACTION" VALUES (transaction_seq.nextval,p_acc_id, null, '-', SYSTIMESTAMP,p_amount,'계좌가 존재하지 않습니다.', 'F',v_new_balance);
p_result := -1;
        p_id := transaction_seq.CURRVAL;
COMMIT;
WHEN OTHERS THEN
        -- 그 외 예외 처리
        DBMS_OUTPUT.PUT_LINE('에러 발생: ' || SQLERRM);
ROLLBACK; -- 롤백 (변경 내용 취소)
v_error_message := SQLERRM;
        p_result := 0;
        v_new_balance:= 0;
INSERT INTO "TRANSACTION" VALUES (transaction_seq.nextval,p_acc_id, null, '-', SYSTIMESTAMP,p_amount,v_error_message, 'F',v_new_balance);
p_id := transaction_seq.CURRVAL;
COMMIT;
END;
