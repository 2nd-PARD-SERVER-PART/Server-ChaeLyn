package pard.hw5th.controller.sheet;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pard.hw5th.dto.ResponseDto;
import pard.hw5th.dto.club.request.ClubCreateRequest;
import pard.hw5th.dto.club.request.ClubUpdateRequest;
import pard.hw5th.dto.sheet.request.SheetCheckedRequest;
import pard.hw5th.dto.sheet.request.SheetCreateRequest;
import pard.hw5th.dto.sheet.request.SheetReturnRequest;
import pard.hw5th.dto.sheet.request.SheetUpdateRequest;
import pard.hw5th.dto.sheet.response.SheetResponse;
import pard.hw5th.entity.Club.Club;
import pard.hw5th.entity.Sheet.Sheet;
import pard.hw5th.service.sheet.SheetService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/handong") // 공통되는 URL 묶기. 왜 다들 /api 붙이는지?
public class SheetController {
    private final SheetService sheetService;

    @PostMapping("/sheetRegister")
    public ResponseDto<Sheet> createSheet(@RequestBody SheetCreateRequest request) {
        ResponseDto<Sheet> result = sheetService.createSheet(request);
        return result;
    }

    @PutMapping("/checkSheet/{sheetId}")
    public ResponseEntity<Sheet> checkSheet(@PathVariable Long sheetId, @RequestBody SheetCheckedRequest request) {
        Sheet borrowedSheet = sheetService.checkSheet(sheetId, request.getClubName());
        return ResponseEntity.status(HttpStatus.OK)
                .body(borrowedSheet);
    }

    @PatchMapping("/return")
    public ResponseEntity<Sheet> returnSheet(@RequestBody SheetReturnRequest request) {
        Sheet returnedSheet = sheetService.returnSheet(request.getSheetId());
        return ResponseEntity.status(HttpStatus.OK).body(returnedSheet);
    }

    @GetMapping("/findsheets")
    public ResponseEntity<List<SheetResponse>> findAllSheets() {
        List<SheetResponse> sheets = sheetService.findAll()
                .stream()
                .map(SheetResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(sheets);
    }

    @GetMapping("/find1sheet/{sheetPlace}")
    public ResponseEntity<List<SheetResponse>> findSheetByPlace(@PathVariable String sheetPlace) {
        List<SheetResponse> sheet = sheetService.findPlace(sheetPlace)
                .stream()
                .map(SheetResponse::new)
                .toList();;
        if (sheet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(sheet);
    }

    @PatchMapping("/sheetupdate/{sheetId}")
    public ResponseDto<Sheet> updateSheet(@PathVariable Long sheetId, @RequestBody SheetUpdateRequest sheetUpdateRequest) {
        ResponseDto<Sheet> result = sheetService.updateSheet(sheetId, sheetUpdateRequest);
        return result;
    }

    @DeleteMapping("/deletesheet/{sheetId}")
    public ResponseEntity<Void> deleteSheet(@PathVariable Long sheetId) {
        sheetService.delete(sheetId);

        return ResponseEntity.ok()
                .build();
    }
}