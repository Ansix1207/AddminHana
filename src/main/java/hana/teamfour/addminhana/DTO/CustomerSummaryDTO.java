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
    private CustomerSessionDTO customerSessionDTO;

    public CustomerSummaryDTO(CustomerEntity customerEntity) {
        this.c_id = customerEntity.getC_id();
        this.c_name = customerEntity.getC_name();
        this.c_rrn = customerEntity.getC_rrn();
        this.c_gender = customerEntity.getC_gender();
        this.c_job = customerEntity.getC_job();
        this.c_description = customerEntity.getC_description();
        this.customerSessionDTO = new CustomerSessionDTO(this.c_name, this.c_id);
    }

    public CustomerSummaryDTO(Integer c_id, String c_name, String c_rrn, Character c_gender, String c_job, String c_description, Integer c_age) {
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_rrn = c_rrn;
        this.c_gender = c_gender;
        this.c_job = c_job;
        this.c_description = c_description;
        this.c_age = c_age;
        this.customerSessionDTO = new CustomerSessionDTO(c_name, c_id);
    }

    public static CustomerSummaryDTO from(CustomerEntity customerEntity) {
        return new CustomerSummaryDTO(customerEntity);
    }

}
