package hana.teamfour.addminhana.DTO;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class ProductJoinSummaryDTO {
    private Integer acc_id;
    private Integer acc_cid;
    private Timestamp acc_date;
    private Integer acc_balance;
    private String acc_password;
    private Integer acc_pid;
    private String acc_p_category;
    private String acc_pname;
    private Double acc_interestrate;
    private Integer acc_collateralvalue;
    private Integer acc_interest_day;
    private Integer acc_contract_month;
    private Timestamp acc_maturitydate;
    private Character acc_isactive;
}
