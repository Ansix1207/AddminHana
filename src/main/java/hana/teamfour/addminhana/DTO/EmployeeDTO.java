package hana.teamfour.addminhana.DTO;

import hana.teamfour.addminhana.entity.EmployeeEntity;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {
    private Integer e_id;
    private String e_name;

    public EmployeeDTO(EmployeeEntity employeeEntity) {
        this.e_id = employeeEntity.getE_id();
        this.e_name = employeeEntity.getE_name();
    }
}
