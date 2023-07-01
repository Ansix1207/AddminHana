package hana.teamfour.addminhana.DTO;

import hana.teamfour.addminhana.entity.ProductEntity;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private String p_category;
    private String p_name;
    private String p_description;
    private Double p_interestrate;

    public ProductDTO(ProductEntity productEntity) {
        this.p_name = productEntity.getP_name();
        this.p_interestrate = productEntity.getP_interestrate();
        this.p_description = productEntity.getP_description();
        this.p_category = productEntity.getP_category();
    }
}
