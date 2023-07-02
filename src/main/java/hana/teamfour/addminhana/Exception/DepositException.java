package hana.teamfour.addminhana.Exception;

import lombok.Getter;

@Getter
public class DepositException extends Exception {
    private int t_id;
    private int errorcode;

    public DepositException() {
    }

    public DepositException(String message, int p_id, int errorcode) {
        super(message);
        this.t_id = p_id;
        this.errorcode = errorcode;
    }
}
