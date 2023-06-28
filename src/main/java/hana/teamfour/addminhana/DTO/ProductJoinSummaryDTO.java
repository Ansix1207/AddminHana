package hana.teamfour.addminhana.DTO;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class ProductJoinSummaryDTO {
    private Integer ACC_CID;
    private Timestamp ACC_DATE;
    private java.lang.String ACC_P_CATEGORY;
    private java.lang.String ACC_PNAME;
    private java.lang.String c_name;
    private java.lang.String c_job;
    private java.lang.String c_mobile;
    private java.lang.String c_description;
}
