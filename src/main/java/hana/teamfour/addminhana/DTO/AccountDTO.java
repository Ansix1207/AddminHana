package hana.teamfour.addminhana.DTO;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private String acc_balance;
    private String acc_pname;
    private Double acc_interestrate;
    private String acc_maturitydate;
}
