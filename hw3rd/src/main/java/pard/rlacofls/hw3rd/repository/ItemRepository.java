package pard.rlacofls.hw3rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pard.rlacofls.hw3rd.dto.ItemAddDto;
import pard.rlacofls.hw3rd.entity.ItemEntity;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
    boolean existsByItemName(String itemName);
    List<ItemEntity> findByItemPriceLessThan(int itemPrice);
    List<ItemEntity> findByItemQuantityLessThan(int itemQuantity);
    List<ItemEntity> findByItemNameContaining(String keyword);
    List<ItemEntity> findByItemPriceBetween(int minPrice, int maxPrice);

}