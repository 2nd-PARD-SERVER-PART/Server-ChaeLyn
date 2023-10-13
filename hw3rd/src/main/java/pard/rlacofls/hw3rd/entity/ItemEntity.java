package pard.rlacofls.hw3rd.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    private Integer itemId;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String itemName; //이름
    private int itemPrice; //가격
    private int itemQuantity;

    @CreationTimestamp
    private Timestamp itemSignUpTime;

    public ItemEntity(ItemAddDto dto){
        this.itemPrice = dto.getItemPrice();
        this.itemName = dto.getItemName();
        this.itemQuantity = dto.getItemQuantity();
    }

}
