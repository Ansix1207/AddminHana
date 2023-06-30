package hana.teamfour.addminhana.DTO;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AssetDTO {
    private Integer ass_deposit;
    private Integer ass_savings;
    private Integer ass_loan;
    private Integer[] balance_sum;
}
