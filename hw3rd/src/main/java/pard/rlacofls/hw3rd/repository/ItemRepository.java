package pard.rlacofls.hw3rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pard.rlacofls.hw3rd.entity.ItemEntity;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
    boolean existsByItemName(String itemName);
}