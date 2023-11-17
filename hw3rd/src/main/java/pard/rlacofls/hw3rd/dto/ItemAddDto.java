package pard.rlacofls.hw3rd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemAddDto {
    private String itemName; // 상품 이름
    private Integer itemPrice; // 상품 가격
    private int itemQuantity; // 상품 수량
}
