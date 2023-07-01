package hana.teamfour.addminhana.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaginationDTO {
    private Integer page;
    private Integer size;
    private String orderBy;
    private String search;
}
