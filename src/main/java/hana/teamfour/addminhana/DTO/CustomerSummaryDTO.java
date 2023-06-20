package hana.teamfour.addminhana.DTO;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSummaryDTO {
    private Integer c_id;
    private String c_name;
    private String c_rrn;
    private Character c_gender;
    private String c_job;
    private String c_description;
}
