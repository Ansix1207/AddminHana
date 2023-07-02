create or replace TRIGGER insert_asset_specific_trigger
AFTER INSERT ON CUSTOMER
FOR EACH ROW
BEGIN
INSERT INTO asset (ASS_ID, C_ID) VALUES (ASSET_SEQ.nextval, :NEW.C_ID);
INSERT INTO specific (C_ID, spc_rank, spc_creditrank) VALUES (:NEW.C_ID, '일반', 5);
END;
