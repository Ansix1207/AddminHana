package hana.teamfour.addminhana.DTO;

import hana.teamfour.addminhana.entity.TransactionEntity;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WithdrawDTO {
    //출금 DTO (입금할 계좌번호 , 거래 금액, 거래 내용
    /**
     * 출금 withdrawal (비밀번호 o )
     * - 출금할 계좌 입력
     * - 계좌 비밀번호 필요함
     * - 비밀번호 입력후 계좌 잔고 표시. ← 리턴
     * - 얼마 출금할지? 거래금액
     * - 거래내용(메모용)
     */
    private Integer acc_id; //출금 계좌
    private String acc_password;//계좌 비밀번호
    private Integer acc_balance; //계좌 잔고 <--비밀번호 입력 후 값을 가짐
    private Integer t_amount; //거래 금액
    private String t_description;// 거래 내용

    private String message;

    private WithdrawDTO(TransactionEntity transactionEntity) {
        if (transactionEntity.getT_accid() != null) {
            this.acc_id = transactionEntity.getT_accid();
        }
        if (transactionEntity.getT_amount() != null) {
            this.t_amount = transactionEntity.getT_amount();
        }
        if (transactionEntity.getT_balance() != null) {
            this.acc_balance = transactionEntity.getT_balance();
        }
        if (transactionEntity.getT_description() != null) {
            this.t_description = transactionEntity.getT_description();
        }
        this.acc_password = null;
    }

    public static WithdrawDTO from(TransactionEntity transactionEntity) {
        return new WithdrawDTO(transactionEntity);
    }
}
