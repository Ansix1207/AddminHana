CREATE OR REPLACE PROCEDURE update_account_balance(
    p_acc_id IN NUMBER,
    p_amount IN NUMBER,
    p_result OUT NUMBER
)
IS
    v_acc_amount NUMBER;
    v_old_balance NUMBER;
    v_new_balance NUMBER;
BEGIN
    -- account 테이블에서 acc_amount 값을 가져옴
    SELECT acc_balance INTO v_acc_amount FROM account WHERE acc_id = p_acc_id;

    -- 가져온 값에 입력받은 p_amount를 더함
    v_old_balance := v_acc_amount;
    v_new_balance := v_acc_amount + p_amount;
    -- 업데이트 수행
    UPDATE account SET acc_balance = v_new_balance WHERE acc_id = p_acc_id;

    -- 커밋 (변경 내용을 영구적으로 저장)
    COMMIT;

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
        DBMS_OUTPUT.PUT_LINE('해당 계정이 존재하지 않습니다.');
        p_result := 0;
    WHEN OTHERS THEN
        -- 그 외 예외 처리
        DBMS_OUTPUT.PUT_LINE('에러 발생: ' || SQLERRM);
        ROLLBACK; -- 롤백 (변경 내용 취소)
        p_result := 0;
END;
/



/**
===여기 아래는 DB 에서 테스트용
SET SERVEROUTPUT ON;

DECLARE
    v_result NUMBER;
BEGIN
    update_account_balance(1, 100, v_result);
    IF v_result = 1 THEN
        DBMS_OUTPUT.PUT_LINE('입금 성공');
    ELSE
        DBMS_OUTPUT.PUT_LINE('입금 실패');
    END IF;
END;
/
*/
