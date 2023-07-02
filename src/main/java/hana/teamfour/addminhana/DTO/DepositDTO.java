package hana.teamfour.addminhana.DTO;

import hana.teamfour.addminhana.entity.TransactionEntity;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositDTO {
    private Integer acc_id; //입금 계좌
    private Integer acc_balance; //계좌 잔고
    private Integer t_amount; //거래 금액
    private String t_description;// 거래 내용

    private DepositDTO(TransactionEntity transactionEntity) {
        this.acc_id = transactionEntity.getT_accid();
        this.t_amount = transactionEntity.getT_amount();
        this.acc_balance = transactionEntity.getT_balance();
        this.t_description = transactionEntity.getT_description();
    }

    public static DepositDTO from(TransactionEntity transactionEntity) {
        return new DepositDTO(transactionEntity);
    }
}
