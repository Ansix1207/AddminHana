package hana.teamfour.addminhana.DTO;

import hana.teamfour.addminhana.entity.CustomerEntity;
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
    private Integer c_age;

    public CustomerSummaryDTO(CustomerEntity customerEntity) {
        this.c_id = customerEntity.getC_id();
        this.c_name = customerEntity.getC_name();
        this.c_rrn = customerEntity.getC_rrn();
        this.c_gender = customerEntity.getC_gender();
        this.c_job = customerEntity.getC_job();
        this.c_description = customerEntity.getC_description();
    }

    public static CustomerSummaryDTO from(CustomerEntity customerEntity) {
        return new CustomerSummaryDTO(customerEntity);
    }

}
