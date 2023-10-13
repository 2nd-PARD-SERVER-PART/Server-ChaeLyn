package pard.rlacofls.hw3rd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public ResponseDto<ItemEntity> addItem(ItemAddDto itemAddDto) {
        ItemEntity item = new ItemEntity(itemAddDto);
        if ((itemRepository.existsByItemName(item.getItemName()))) {
            return ResponseDto.setFailed("같은 이름의 품목이 있습니다");

        } else if(item.getItemPrice() >= 50000){
            return ResponseDto.setFailed("너무 비쌉니다.");
        }else{
            itemRepository.save(item);
            return ResponseDto.setSuccess("성공 ㅊㅊ", item);

        }
    }

    public ResponseDto<List<ItemEntity>> findAll() {
        List<ItemEntity> items;
        try {
            items = itemRepository.findAll();
            return ResponseDto.setSuccess("ㅊㅋㅊㅋ", items);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("DB 오류");
        }
    }

    public ResponseDto<ItemEntity> findOne(Integer itemId) {
        ItemEntity item;
        try {
            item = itemRepository.findById(itemId).get();
            return ResponseDto.setSuccess("꺄항~", item);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("DB 오류입니다 다시 한번 확인해주세요");
        }
    }
    public ResponseDto<ItemEntity> updateItem(Integer itemId, ItemAddDto itemAddDto) {
        ItemEntity item;
        try {
            item = itemRepository.findById(itemId).get(); //바꿀 아이템 아이디 불러온 것 item에 저장되어있음...
            if (!itemAddDto.getItemName().isEmpty()) item.setItemName(itemAddDto.getItemName());
            if (itemAddDto.getItemPrice() >= 0 && itemAddDto.getItemPrice() < 50000)
                item.setItemPrice(itemAddDto.getItemPrice());
            if (itemAddDto.getItemQuantity() >= 0) item.setItemQuantity(itemAddDto.getItemQuantity());
            itemRepository.save(item);
            return ResponseDto.setSuccess("ㅊㅊ", item);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("DB 오류");
        }
    }

    public ResponseDto<?> deleteItem(Integer itemId) {
        try {
            if (itemRepository.existsById(itemId)) {
                itemRepository.deleteById(itemId);
                return ResponseDto.setSuccess("얏호", null);
            }
            return ResponseDto.setFailed("존재하지 않는 id");
        } catch (Exception e) {
            return ResponseDto.setFailed("ㅠㅠ");
        }
    }

}
