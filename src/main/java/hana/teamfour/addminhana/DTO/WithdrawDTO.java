package hana.teamfour.addminhana.DTO;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WithdrawlDTO {
    //출금 DTO (입금할 계좌번호 , 거래 금액, 거래 내용
    /**
     * 출금 withdrawal (비밀번호 o )
     * <p>
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

//    // static factory method pattern (정적 팩토리 메서드 패턴)
//    public static WithdrawlDTO() {
//
//    }
}
