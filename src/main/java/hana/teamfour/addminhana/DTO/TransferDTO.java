package hana.teamfour.addminhana.DTO;

import hana.teamfour.addminhana.entity.TransactionEntity;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferDTO {
    private Integer acc_id; //계좌
    private Integer acc_password; //계좌 비밀번호
    private Integer t_counterpart_id; //이체할 계좌
    private Integer t_amount; //거래 금액
    private String t_description;// 거래 내용
    private int errorcode;
    private int t_id;
    private String message;

    private TransferDTO(TransactionEntity transactionEntity) {
        this.acc_id = transactionEntity.getT_accid();
        this.t_counterpart_id = transactionEntity.getT_counterpart_id();
        this.t_amount = transactionEntity.getT_amount();
        this.t_description = transactionEntity.getT_description();
    }

    public static TransferDTO from(TransactionEntity transactionEntity) {
        return new TransferDTO(transactionEntity);
    }
}
