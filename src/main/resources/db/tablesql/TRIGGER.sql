
--입출금 trigger
DROP TRIGGER set_t_balance;
CREATE OR REPLACE TRIGGER set_t_balance
BEFORE INSERT ON TRANSACTION
FOR EACH ROW
DECLARE 
    v_acc_balance NUMBER;
BEGIN
    -- Check the value of t_type
    IF :NEW.t_type = '+' THEN
        -- Update the acc_balance in ACCOUNT table by adding t_amount
        UPDATE ACCOUNT
        SET acc_balance = acc_balance + :NEW.t_amount
        WHERE acc_id = :NEW.t_accid;
    ELSIF :NEW.t_type = '-' THEN
        -- Update the acc_balance in ACCOUNT table by subtracting t_amount
        UPDATE ACCOUNT
        SET acc_balance = acc_balance - :NEW.t_amount
        WHERE acc_id = :NEW.t_accid;
    END IF;

    -- Get the updated acc_balance value
    SELECT acc_balance INTO v_acc_balance
    FROM ACCOUNT
    WHERE acc_id = :NEW.t_accid;

    -- Set the updated acc_balance value to t_balance column
    :NEW.t_balance := v_acc_balance;
END;
/