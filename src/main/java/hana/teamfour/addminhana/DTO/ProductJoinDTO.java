package hana.teamfour.addminhana.DTO;

import hana.teamfour.addminhana.entity.AccountEntity;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductJoinDTO {
    private Integer acc_id;
//    private Integer acc_cid;
//    private Timestamp acc_date;
//    private Integer acc_balance;
//    private String acc_password;
//    private Integer acc_pid;
//    private String acc_p_category;
//    private String acc_pname;
//    private Double acc_interestrate;
//    private Integer acc_collateralvalue;
//    private Integer acc_interest_day;
//    private Integer acc_contract_month;
//    private Timestamp acc_maturitydate;
//    private Character acc_isactive;

    private ProductJoinDTO(AccountEntity accountEntity) {
        this.acc_id = accountEntity.getAcc_id();
//        this.acc_cid = accountEntity.getAcc_cid();
//        this.acc_date = accountEntity.getAcc_date();
//        this.acc_balance = accountEntity.getAcc_balance();
//        this.acc_password = accountEntity.getAcc_password();
//        this.acc_pid = accountEntity.getAcc_pid();
//        this.acc_p_category = accountEntity.getAcc_p_category();
//        this.acc_pname = accountEntity.getAcc_pname();
//        this.acc_interestrate = accountEntity.getAcc_interestrate();
//        this.acc_collateralvalue = accountEntity.getAcc_collateralvalue();
//        this.acc_interest_day = accountEntity.getAcc_interest_day();
//        this.acc_contract_month = accountEntity.getAcc_contract_month();
//        this.acc_maturitydate = accountEntity.getAcc_maturitydate();
//        this.acc_isactive = accountEntity.getAcc_isactive();
    }

    // static factory method pattern (정적 팩토리 메서드 패턴)
//    public static ProductJoinDTO from(AccountEntity accountEntity) {
//        return new ProductJoinDTO(accountEntity);
//    }

}
