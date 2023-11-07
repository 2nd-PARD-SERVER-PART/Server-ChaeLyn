package pard.hw5th.service.sheet;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pard.hw5th.dto.ResponseDto;
import pard.hw5th.dto.club.request.ClubCreateRequest;
import pard.hw5th.dto.club.request.ClubUpdateRequest;
import pard.hw5th.dto.sheet.request.SheetCreateRequest;
import pard.hw5th.dto.sheet.request.SheetUpdateRequest;
import pard.hw5th.entity.Club.Club;
import pard.hw5th.entity.Sheet.Sheet;
import pard.hw5th.repository.Club.ClubRepository;
import pard.hw5th.repository.Sheet.SheetRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SheetService {

    private final SheetRepository sheetRepository;
    private final ClubRepository clubRepository;

    public ResponseDto<Sheet> createSheet(SheetCreateRequest sheetCreateRequest) {
        Sheet newSheet = sheetCreateRequest.toEntity();
        try {
            if (sheetRepository.existsByPlace(sheetCreateRequest.getPlace())&&sheetRepository.existsByTime(sheetCreateRequest.getTime())&&sheetRepository.existsByDay(sheetCreateRequest.getDay())) {
                return ResponseDto.setFailed("해당 시트는 이미 존재합니다");
            }
            sheetRepository.save(newSheet);
            return ResponseDto.setSuccess("동아리가 추가되었습니다", newSheet);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("데이터 베이스 오류");
        }
    }

    @Transactional
    public Sheet checkSheet(Long sheetId, String clubName) {
        Sheet sheet = sheetRepository.findById(sheetId)
                .orElseThrow(() -> new IllegalArgumentException("시트가 존재하지 않습니다."));

        if (sheet.isChecked()) {
            throw new IllegalStateException("이미 빌려진 시트입니다.");
        }

        Club club = clubRepository.findByName(clubName)
                .orElseThrow(() -> new IllegalArgumentException("동아리가 존재하지 않습니다."));

        sheet.setClub(club);
        club.getCheckedSheet().add(sheet);

        return sheetRepository.save(sheet);
    }

    public Sheet returnSheet(Long sheetId){
        Sheet sheet = sheetRepository.findById(sheetId)
                .orElseThrow(() -> new IllegalArgumentException("해당 시트가 없습니다."));

        if (sheet.getClub() == null) {
            throw new IllegalStateException("빌릴 수 있는 시트입니다.");
        }

        Club club = sheet.getClub();

        club.getCheckedSheet().remove(sheet);
        sheet.setClub(null);

        return sheetRepository.save(sheet);
    }

    public List<Sheet> findAll(){
        return sheetRepository.findAll();
    }

    public List<Sheet> findPlace(String sheetPlace) {
        return sheetRepository.findByPlace(sheetPlace);
    }

    @Transactional
    public ResponseDto<Sheet> updateSheet(Long sheetId, SheetUpdateRequest sheetUpdateRequest) {
        Sheet sheet;
        try{
            sheet = sheetRepository.findById(sheetId).get();
            if (!sheetRepository.existsById(sheetId)) {
                return ResponseDto.setFailed("없는 시트의 Id입니다.");
            }
            if(!sheet.getPlace().isEmpty())
                sheet.setPlace(sheetUpdateRequest.getPlace());
            if(!sheet.getTime().isEmpty())
                sheet.setTime(sheetUpdateRequest.getTime());
            if(!sheet.getDay().isEmpty())
                sheet.setDay(sheetUpdateRequest.getDay());
            sheetRepository.save(sheet);
            return ResponseDto.setSuccess("성공적으로 업데이트 되었습니다", sheet);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("데이터 베이스 오류");

        }
    }

    public void delete(Long sheetId) {
        sheetRepository.deleteById(sheetId);
    }
}
