package pard.rlacofls.hw3rd.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pard.rlacofls.hw3rd.dto.ItemAddDto;
import pard.rlacofls.hw3rd.dto.ResponseDto;
import pard.rlacofls.hw3rd.entity.ItemEntity;
import pard.rlacofls.hw3rd.service.ItemService;

import java.util.List;

@RestController
    public class ItemController {
        private final ItemService itemService;

        @Autowired
        public ItemController(ItemService itemService) {
            this.itemService = itemService;
        }

        // create item
        //RequestBody로 입력 받기에 json 파일로 보내줘야함
        @PostMapping("/additem")
        public ResponseDto<ItemEntity> addItem(@RequestBody ItemAddDto dto) {
            ResponseDto<ItemEntity> result = itemService.addItem(dto);
            return result;
        }

        @GetMapping("/findall")
        public ResponseDto<List<ItemEntity>> findAll() {
            ResponseDto<List<ItemEntity>> result = itemService.findAll();
            return result;
        }

        // PathVariable이므로 api에 해당 id 입력 필요
        @GetMapping("/findone/{itemId}")
        public ResponseDto<ItemEntity> findOne(@PathVariable Integer itemId) {
            ResponseDto<ItemEntity> result = itemService.findOne(itemId);
            return result;
        }

        // 바꿀 품목의 id - PathVariable 바꿀 내용 - RequestBody
        @PatchMapping("/updateitem/{itemId}")
        public ResponseDto<ItemEntity> updateItem(@PathVariable Integer itemId, @RequestBody ItemAddDto dto) {
            ResponseDto<ItemEntity> result = itemService.updateItem(itemId, dto);
            return result;
        }

        @DeleteMapping("/deleteitem/{itemId}")
        public ResponseDto<?> deleteItem(@PathVariable Integer itemId) {
            ResponseDto<?> result = itemService.deleteItem(itemId);
            return result;
        }

    //jpa 구문 만든 부분
    @GetMapping("/lessprice/{itemPrice}")
    public ResponseDto<List<ItemEntity>> findItemEntitiesByItemPriceLessThan(@PathVariable int itemPrice) {
        ResponseDto<List<ItemEntity>> result = itemService.findItemEntitiesByItemPriceLessThan(itemPrice);
        return result;
    }

    @GetMapping("/lessquantity/{itemQuantity}")
    public ResponseDto<List<ItemEntity>> findByItemQuantityLessThan(@PathVariable int itemQuantity) {
        ResponseDto<List<ItemEntity>> result = itemService.findByItemQuantityLessThan(itemQuantity);
        return result;
    }

    @GetMapping("/findkeyword/{keyword}")
    public ResponseDto<List<ItemEntity>> findByItemNameContaining(@PathVariable String keyword) {
        ResponseDto<List<ItemEntity>> result = itemService.findByItemNameContaining(keyword);
        return result;
    }

    @GetMapping("/pricecheck/{minPrice}/{maxPrice}")
    public ResponseDto<List<ItemEntity>> findByItemPriceBetween(@PathVariable int minPrice,
                                                                @PathVariable int maxPrice) {
        ResponseDto<List<ItemEntity>> result = itemService.findByItemPriceBetween(minPrice,maxPrice);
        return result;
    }
}
