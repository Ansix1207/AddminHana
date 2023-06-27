package hana.teamfour.addminhana.DTO;

import hana.teamfour.addminhana.entity.CustomerEntity;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSessionDTO {
    private String c_name;
    private int c_id;

    public CustomerSessionDTO(CustomerEntity customerEntity) {
        this.c_name = customerEntity.getC_name();
        this.c_id = customerEntity.getC_id();
    }

    public static CustomerSessionDTO from(CustomerEntity customerEntity) {
        return new CustomerSessionDTO(customerEntity);
    }
}
