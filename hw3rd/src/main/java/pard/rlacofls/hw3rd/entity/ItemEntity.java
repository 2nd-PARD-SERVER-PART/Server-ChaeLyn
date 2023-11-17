package pard.rlacofls.hw3rd.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pard.rlacofls.hw3rd.dto.ItemAddDto;

import java.sql.Timestamp;

@Entity(name = "item")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 알아서 증가
    private Integer itemId; // 엔티티의 Pimary Key

    @Column(columnDefinition = "TEXT", nullable = false)
    private String itemName; //이름
    private int itemPrice; //가격
    private int itemQuantity; //수량

    @CreationTimestamp
    private Timestamp itemSignUpTime; // 등록된 시간

    public ItemEntity(ItemAddDto dto){
        this.itemPrice = dto.getItemPrice();
        this.itemName = dto.getItemName();
        this.itemQuantity = dto.getItemQuantity();
    } //Entity to Dto

}
