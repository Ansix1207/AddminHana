package hana.teamfour.addminhana.Exception;

import lombok.Getter;

@Getter
public class BalanceInsufficientException extends Exception {
    private int t_id;
    private int errorcode;

    public BalanceInsufficientException() {
    }

    public BalanceInsufficientException(String message, int p_id, int errorcode) {
        super(message);
        this.t_id = p_id;
        this.errorcode = errorcode;
    }
}
