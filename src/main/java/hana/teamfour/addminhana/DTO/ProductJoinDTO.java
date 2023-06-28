package hana.teamfour.addminhana.DTO;

import hana.teamfour.addminhana.entity.AccountEntity;
import hana.teamfour.addminhana.entity.CustomerEntity;
import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductJoinDTO {
    private Integer ACC_CID;
    private Timestamp ACC_DATE;
    private java.lang.String ACC_P_CATEGORY;
    private java.lang.String ACC_PNAME;
    private java.lang.String c_name;
    private java.lang.String c_job;
    private java.lang.String c_mobile;
    private java.lang.String c_description;


    private ProductJoinDTO(CustomerEntity customerEntity) {
        this.c_name = customerEntity.getC_name();
        this.c_job = customerEntity.getC_job();
        this.c_mobile = customerEntity.getC_mobile();
        this.c_description = customerEntity.getC_description();
    }

    private ProductJoinDTO(AccountEntity accountEntity) {
        this.ACC_CID = accountEntity.getAcc_cid();
        this.ACC_DATE = accountEntity.getAcc_date();
        this.ACC_P_CATEGORY = accountEntity.getAcc_p_category();
        this.ACC_PNAME = accountEntity.getAcc_pname();
    }

    // static factory method pattern (정적 팩토리 메서드 패턴)
    public static ProductJoinDTO from(CustomerEntity customerEntity) {
        return new ProductJoinDTO(customerEntity);
    }

    public static ProductJoinDTO from(AccountEntity customerEntity) {
        return new ProductJoinDTO(customerEntity);
    }

    public static CustomerEntity toEntity(ProductJoinDTO productJoinDTO) {
        return CustomerEntity.builder()
                .ACC_CID(productJoinDTO.getACC_CID())
                .ACC_DATE(productJoinDTO.getACC_DATE())
                .ACC_P_CATEGORY(productJoinDTO.getACC_P_CATEGORY())
                .ACC_PNAME(productJoinDTO.getACC_PNAME())
                .c_name(productJoinDTO.getC_name())
                .c_job(productJoinDTO.getC_job())
                .c_mobile(productJoinDTO.getC_mobile())
                .c_description(productJoinDTO.getC_description())
                .build();
    }
}
