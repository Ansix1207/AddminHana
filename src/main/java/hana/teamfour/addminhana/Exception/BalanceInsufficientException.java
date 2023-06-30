package hana.teamfour.addminhana.Exception;

import lombok.Getter;

@Getter
public class BalanceInsufficientException extends Exception {
    private int t_id;

    public BalanceInsufficientException() {
    }

    public BalanceInsufficientException(String message, int p_id) {
        super(message);
        this.t_id = p_id;
    }
}
