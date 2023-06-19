package hana.teamfour.addminhana.entity;


import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentHistoryEntity {
    private Integer pay_id;
    private Integer c_id;
    private Timestamp pay_date;
    private Integer pay_amount;
    private String pay_type;
}
