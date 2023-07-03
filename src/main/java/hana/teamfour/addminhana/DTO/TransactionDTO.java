package hana.teamfour.addminhana.DTO;

import hana.teamfour.addminhana.entity.TransactionEntity;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Integer t_id;
    private Integer t_accid;
    private Integer t_counterpart_id;
    private Character t_type;
    private Timestamp t_date;
    private Integer t_amount;
    private String t_description;
    private Character t_status;
    private Integer t_balance;

    public TransactionDTO(TransactionEntity transactionEntity) {
        this.t_id = transactionEntity.getT_id();
        this.t_accid = transactionEntity.getT_accid();
        this.t_counterpart_id = transactionEntity.getT_counterpart_id();
        this.t_type = transactionEntity.getT_type();
        this.t_date = transactionEntity.getT_date();
        this.t_amount = transactionEntity.getT_amount();
        this.t_description = transactionEntity.getT_description();
        this.t_status = transactionEntity.getT_status();
        this.t_balance = transactionEntity.getT_balance();
    }
}
