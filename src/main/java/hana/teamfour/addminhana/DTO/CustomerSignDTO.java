package hana.teamfour.addminhana.DTO;

import hana.teamfour.addminhana.entity.CustomerEntity;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerSignDTO {
    //entity(id,name,rrn,gender,address,mobile,job,escription,e_id)
    private String c_name;
    private String c_rrn;
    private Character c_gender;
    private String c_address;
    private String c_mobile;
    private String c_job;
    private String c_description;
    private int e_id;
    private CustomerSignDTO(CustomerEntity customerEntity){
        this.c_name = customerEntity.getC_name();
        this.c_rrn = customerEntity.getC_rrn();
        this.c_gender = customerEntity.getC_gender();
        this.c_address = customerEntity.getC_address();
        this.c_mobile = customerEntity.getC_mobile();
        this.c_job = customerEntity.getC_job();
        this.c_description = customerEntity.getC_description();
    }

    // static factory method pattern (정적 팩토리 메서드 패턴)
    public static CustomerSignDTO from(CustomerEntity customerEntity) {
        return new CustomerSignDTO(customerEntity);
    }

    public static CustomerEntity toEntity(CustomerSignDTO customerSignDTO) {
        return CustomerEntity.builder()
                .c_id(-999)
                .c_name(customerSignDTO.getC_name())
                .c_rrn(customerSignDTO.getC_rrn())
                .c_gender(customerSignDTO.getC_gender())
                .c_address(customerSignDTO.getC_address())
                .c_mobile(customerSignDTO.getC_mobile())
                .c_job(customerSignDTO.getC_job())
                .c_description(customerSignDTO.getC_description())
                .e_id(customerSignDTO.getE_id())
                .build();
    }
}
