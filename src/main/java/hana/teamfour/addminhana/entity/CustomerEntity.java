package hana.teamfour.addminhana.entity;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
    private Integer c_id;
    private String c_name;
    private String c_rrn;
    private Character c_gender;
    private String c_address;
    private String c_mobile;
    private String c_job;
    private String c_description;
    private Integer e_id;
}
