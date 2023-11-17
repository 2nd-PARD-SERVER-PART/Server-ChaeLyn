package pard.rlacofls.hw3rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pard.rlacofls.hw3rd.dto.ItemAddDto;
import pard.rlacofls.hw3rd.entity.ItemEntity;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
    boolean existsByItemName(String itemName);

    List<ItemEntity> findByItemPriceLessThan(int itemPrice); // 가격 n원 이하인 물품 출력

    List<ItemEntity> findByItemQuantityLessThan(int itemQuantity); // 수량 n개 이하인 물품 출력

    List<ItemEntity> findByItemNameContaining(String keyword); // 해당 키워드 있는 물품 출력

    List<ItemEntity> findByItemPriceBetween(int minPrice, int maxPrice); // 최소 최대 가격 사이에 있는 물품 출력

}