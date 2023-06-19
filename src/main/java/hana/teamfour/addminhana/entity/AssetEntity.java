package hana.teamfour.addminhana.entity;


import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AssetEntity {
    private Integer ass_id;
    private Integer c_id;
    private Integer ass_stock;
    private Integer ass_bond;
    private Integer ass_realestate;
    private Integer ass_deposit;
    private Integer ass_savings;
    private Integer ass_loan;
    private Integer ass_total;
    private Timestamp ass_lastupdate;
}
