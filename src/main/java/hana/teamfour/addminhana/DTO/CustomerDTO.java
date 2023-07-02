package hana.teamfour.addminhana.DTO;

import hana.teamfour.addminhana.entity.CustomerEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Integer c_id;
    private String c_name;
    private String c_rrn;
    private Character c_gender;
    private String c_address;
    private String c_mobile;
    private String c_job;
    private String c_description;
    private Integer e_id;

    public CustomerDTO(CustomerEntity customerEntity) {
        this.c_id = customerEntity.getC_id();
        this.c_name = customerEntity.getC_name();
        this.c_rrn = customerEntity.getC_rrn();
        this.c_gender = customerEntity.getC_gender();
        this.c_address = customerEntity.getC_address();
        this.c_mobile = customerEntity.getC_mobile();
        this.c_job = customerEntity.getC_job();
        this.c_description = customerEntity.getC_description();
        this.e_id = customerEntity.getE_id();
    }
}
