package pard.rlacofls.hw3rd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pard.rlacofls.hw3rd.dto.ItemAddDto;
import pard.rlacofls.hw3rd.dto.ResponseDto;
import pard.rlacofls.hw3rd.entity.ItemEntity;
import pard.rlacofls.hw3rd.repository.ItemRepository;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ResponseDto<ItemEntity> addItem(ItemAddDto itemAddDto) { // Create item
        ItemEntity item = new ItemEntity(itemAddDto);
        if ((itemRepository.existsByItemName(item.getItemName()))) { // 상품명 중복 방지
            return ResponseDto.setFailed("같은 이름의 품목이 있습니다");

        } else if(item.getItemPrice() >= 50000){ //5만원 이내의 상품만 등록 가능
            return ResponseDto.setFailed("가격 조정 필요(50000원 이내)");
        }else{
            itemRepository.save(item);
            return ResponseDto.setSuccess("추가되었습니다", item);

        }
    }

    public ResponseDto<List<ItemEntity>> findAll() { // 전체 리스트 출력
        List<ItemEntity> items;
        try {
            items = itemRepository.findAll();
            return ResponseDto.setSuccess("모든 item 정렬", items);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("DB 오류");
        }
    }

    public ResponseDto<ItemEntity> findOne(Integer itemId) { // 입력 받은 ID에 해당하는 상품 출력
        ItemEntity item;
        try {
            item = itemRepository.findById(itemId).get();
            return ResponseDto.setSuccess("itemId 가 " + itemId + "인 item 출력", item);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("DB 오류");
        }
    }
    public ResponseDto<ItemEntity> updateItem(Integer itemId, ItemAddDto itemAddDto) { // 입력받은 ID에 해당하는 상품 수정
        ItemEntity item;
        try {
            item = itemRepository.findById(itemId).get();
            if (!itemAddDto.getItemName().isEmpty()) item.setItemName(itemAddDto.getItemName()); // 이름을 빈칸으로 입력하면, 이전의 이름 그대로 유지
            if (itemAddDto.getItemPrice() >= 0 && itemAddDto.getItemPrice() < 50000) // 가격은 0원 이상 5만원 이하
                item.setItemPrice(itemAddDto.getItemPrice());
            if (itemAddDto.getItemQuantity() >= 0) item.setItemQuantity(itemAddDto.getItemQuantity()); // 수량이 0개 이상이어야함
            itemRepository.save(item);
            return ResponseDto.setSuccess("품목이 업데이트 되었습니다", item);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("DB 오류");
        }
    }

    public ResponseDto<?> deleteItem(Integer itemId) {
        try {
            if (itemRepository.existsById(itemId)) {
                itemRepository.deleteById(itemId);
                return ResponseDto.setSuccess("삭제 되었습니다.", null);
            }
            return ResponseDto.setFailed("존재하지 않는 id입니다"); // 리스트에 있는 ID인지 확인
        } catch (Exception e) {
            return ResponseDto.setFailed("DB 오류");
        }
    }

    // 입력 받은 가격 이하의 품목들 리스트 출력
    public ResponseDto<List<ItemEntity>> findItemEntitiesByItemPriceLessThan(int price) {
        List<ItemEntity> items;
        try {
            items = itemRepository.findByItemPriceLessThan(price);
            return ResponseDto.setSuccess("가격이 " + price + " 이하인 아이템을 id순으로", items);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("DB 오류");
        }
    }

    // 입력 받은 수량 이하의 품목들 리스트 출력
    public ResponseDto<List<ItemEntity>> findByItemQuantityLessThan(int quantity){
        List<ItemEntity> items;
        try {
            items = itemRepository.findByItemQuantityLessThan(quantity);
            return ResponseDto.setSuccess("수량이 " + quantity + "이하인 아이템을 id순으로 정렬함", items);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("DB 오류");
        }
    }

    // 해당 키워드 가지고 있는 품목 출력
    public ResponseDto<List<ItemEntity>> findByItemNameContaining(String keyword) {
        List<ItemEntity> items;
        try {
            items = itemRepository.findByItemNameContaining(keyword);
            return ResponseDto.setSuccess(keyword + "가 포함된 item 정렬", items);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("DB 오류");
        }
    }

    // minPrice와 maxPrice 사이의 품목 출력
    public ResponseDto<List<ItemEntity>> findByItemPriceBetween(int minPrice, int maxPrice) {
        List<ItemEntity> items;
        try {
            items = itemRepository.findByItemPriceBetween(minPrice, maxPrice);
            return ResponseDto.setSuccess("가격이 " + minPrice + " 와 " + maxPrice + "사이에 있는 item 정렬", items);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("DB 오류");
        }
    }

}
