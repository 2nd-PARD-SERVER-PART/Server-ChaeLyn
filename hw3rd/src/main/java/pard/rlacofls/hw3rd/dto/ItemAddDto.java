package pard.rlacofls.hw3rd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemAddDto {
    private String itemName;
    private Integer itemPrice;
    private int itemQuantity;
}
