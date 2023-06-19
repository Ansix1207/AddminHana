package hana.teamfour.addminhana.entity;


import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    private Integer p_id;
    private String p_category;
    private String p_name;
    private String p_description;
    private Integer p_interestrate;
    private Integer p_intrest_day;
    private Timestamp p_date;
    private Integer p_contract_month;
    private Character p_isactive;
    private Integer p_limit;
    private Integer p_collateralrate;
    private Integer p_mincreditgrade;
    private String p_jobtype;
}
