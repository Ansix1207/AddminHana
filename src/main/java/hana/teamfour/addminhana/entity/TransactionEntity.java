package hana.teamfour.addminhana.entity;


import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionEntity {
    private Integer t_id;
    private Integer t_accid;
    private Integer t_counterpart_id;
    private Character t_type;
    private Timestamp t_date;
    private Integer t_amount;
    private String t_description;
    private Character t_status;
    private Integer t_balance;
}
