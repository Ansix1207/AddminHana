package hana.teamfour.addminhana.Exception;

import lombok.Getter;

@Getter
public class TransferException extends Exception {
    private int t_id;
    private int errorcode;

    public TransferException() {
    }

    public TransferException(String message, int t_id, int errorcode) {
        super(message);
        this.t_id = t_id;
        this.errorcode = errorcode;
    }
}
