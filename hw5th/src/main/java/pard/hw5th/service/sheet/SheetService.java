package pard.hw5th.service.sheet;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pard.hw5th.dto.sheet.request.SheetCreateRequest;
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

    public Sheet save(SheetCreateRequest request) {
        return sheetRepository.save(request.toEntity());
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

    public void delete(Long sheetId) {
        sheetRepository.deleteById(sheetId);
    }
}
