package pard.hw5th.controller.club;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pard.hw5th.dto.ResponseDto;
import pard.hw5th.dto.club.request.ClubCreateRequest;
import pard.hw5th.dto.club.request.ClubUpdateRequest;
import pard.hw5th.dto.club.response.ClubResponse;
import pard.hw5th.entity.Club.Club;
import pard.hw5th.service.club.ClubService;

import java.util.List;

@RequiredArgsConstructor // 이건 뭐징?
@RestController
@RequestMapping("/handong") // 공통되는 URL 묶기. 왜 다들 /api 붙이는지?
public class ClubController {
    private final ClubService clubService;

    @PostMapping("/clubIn")
    public ResponseDto<Club> createClub(@RequestBody ClubCreateRequest request){
        ResponseDto<Club> result = clubService.createClub(request);
        return result;
    }

    @GetMapping("/findAll")
    public ResponseDto<List<Club>> findAll(){
        ResponseDto<List<Club>> result = clubService.findAll();
        return result;
    }

    @GetMapping("/find1club/{clubName}")
    public ResponseDto<Club> findClub(@PathVariable String clubName) {
        ResponseDto<Club> result = clubService.findOne(clubName);
        return result;
    }

    @PatchMapping("/clubupdate/{clubName}")
    public ResponseDto<Club> updateClub(@PathVariable String clubName, @RequestBody ClubUpdateRequest request) {
        ResponseDto<Club> result = clubService.updateClub(clubName, request);
        return result;
    }

    @DeleteMapping("/deleteclub/{clubId}")
    public ResponseDto<?> deleteClub(@PathVariable Long clubId) {
        ResponseDto<?> result = clubService.deleteClub(clubId);
        return result;
    }
}

