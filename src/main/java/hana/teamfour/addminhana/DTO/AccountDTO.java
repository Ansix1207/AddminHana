package hana.teamfour.addminhana.DTO;

import hana.teamfour.addminhana.entity.AccountEntity;
import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {
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
    private String acc_str_balance;
    private String acc_str_maturitydate;

    public AccountDTO(Integer acc_id, Integer acc_cid, Timestamp acc_date, Integer acc_balance, String acc_password, Integer acc_pid, String acc_p_category, String acc_pname, Double acc_interestrate, Integer acc_collateralvalue, Integer acc_interest_day, Integer acc_contract_month, Timestamp acc_maturitydate, Character acc_isactive) {
        this.acc_id = acc_id;
        this.acc_cid =  acc_cid;
        this.acc_date =  acc_date;
        this.acc_balance =  acc_balance;
        this.acc_password =  acc_password;
        this.acc_pid =  acc_pid;
        this.acc_p_category =  acc_p_category;
        this.acc_pname =  acc_pname;
        this.acc_interestrate =  acc_interestrate;
        this.acc_collateralvalue =  acc_collateralvalue;
        this.acc_interest_day =  acc_interest_day;
        this.acc_contract_month =  acc_contract_month;
        this.acc_maturitydate =  acc_maturitydate;
        this.acc_isactive =  acc_isactive;
    }

    public static AccountEntity toEntity(AccountDTO accountDTO) {
        return AccountEntity.builder()
                .acc_id(accountDTO.getAcc_id())
                .acc_cid(accountDTO.getAcc_cid())
                .acc_date(accountDTO.getAcc_date())
                .acc_balance(accountDTO.getAcc_balance())
                .acc_password(accountDTO.getAcc_password())
                .acc_pid(accountDTO.getAcc_pid())
                .acc_p_category(accountDTO.getAcc_p_category())
                .acc_pname(accountDTO.getAcc_pname())
                .acc_interestrate(accountDTO.getAcc_interestrate())
                .acc_collateralvalue(accountDTO.getAcc_collateralvalue())
                .acc_interest_day(accountDTO.getAcc_interest_day())
                .acc_contract_month(accountDTO.getAcc_contract_month())
                .acc_maturitydate(accountDTO.getAcc_maturitydate())
                .acc_isactive(accountDTO.getAcc_isactive())
                .build();
    }

    public AccountDTO(String acc_str_balance, String acc_pname, Double acc_interestrate, String acc_str_maturitydate, Integer acc_id) {
        this.acc_str_balance = acc_str_balance;
        this.acc_pname = acc_pname;
        this.acc_interestrate = acc_interestrate;
        this.acc_str_maturitydate = acc_str_maturitydate;
        this.acc_id = acc_id;
    }
}
