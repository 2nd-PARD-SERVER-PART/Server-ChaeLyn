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

    @GetMapping("/findone/{itemId}")
    public ResponseDto<ItemEntity> findOne(@PathVariable Integer itemId) {
        ResponseDto<ItemEntity> result = itemService.findOne(itemId);
        return result;
    }

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

    //jpa 구문 만들어보깅
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
