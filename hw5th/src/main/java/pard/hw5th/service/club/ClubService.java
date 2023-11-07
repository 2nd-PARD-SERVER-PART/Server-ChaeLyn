package pard.hw5th.service.club;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pard.hw5th.dto.ResponseDto;
import pard.hw5th.dto.club.request.ClubCreateRequest;
import pard.hw5th.dto.club.request.ClubUpdateRequest;
import pard.hw5th.entity.Club.Club;
import pard.hw5th.repository.Club.ClubRepository;

import java.util.List;

@Service
@RequiredArgsConstructor // 이게 뭐라고?
public class ClubService {
    private final ClubRepository clubRepository;

    public ResponseDto<Club> createClub(ClubCreateRequest clubCreateRequest) {
        String clubName = clubCreateRequest.getName();
        Club club = new Club(clubName, clubCreateRequest.getNum());
        try {
            if (clubRepository.existsByName(clubName)) {
                return ResponseDto.setFailed("같은 이름의 동아리가 이미 존재합니다");
            }
            clubRepository.save(club);
            return ResponseDto.setSuccess("동아리가 추가되었습니다", club);
        } catch (Exception e) {
            e.printStackTrace(); //나 뭘 틀렸는지 아려주셈
            return ResponseDto.setFailed("데이터 베이스 오류");
        }
    }

    public ResponseDto<List<Club>> findAll() {
        List<Club> clubs;
        try {
            clubs = clubRepository.findAll();
            if(clubs.isEmpty())
                return ResponseDto.setFailed("리스트가 비어있습니다.");
            return ResponseDto.setSuccess("동아리 리스트 입니다",clubs);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("데이터 베이스 오류");
        }
    }

    public ResponseDto<Club> findOne(String clubName) {
        Club club;
        try{
            club = clubRepository.findByName(clubName).get();
            return ResponseDto.setSuccess("동아리 찾기 성공!", club);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("데이터 베이스 오류");
        }
    }

    @Transactional
    public ResponseDto<Club> updateClub(String clubName, ClubUpdateRequest request) {
        Club club;
        try{
            club = clubRepository.findByName(clubName).get();
            if (!clubRepository.existsByName(clubName)) {
                return ResponseDto.setFailed("해당 이름의 동아리가 없습니다.");
            }
            if(!request.getName().isEmpty())
                club.setName(request.getName());
            if(request.getNum() != null)
                club.setNum(request.getNum());
            clubRepository.save(club);
            return ResponseDto.setSuccess("성공적으로 업데이트 되었습니다", club);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("데이터 베이스 오류");

        }
    }

    public ResponseDto<?> deleteClub(Long clubId) {
        try{
            if (clubRepository.existsById(clubId)) {
                Club club = clubRepository.findById(clubId).get();
                club.delete();
                clubRepository.deleteById(clubId);
                return ResponseDto.setSuccess("성공적으로 삭제 되었습니다.", null);
            }
            return ResponseDto.setFailed("해당 Id에 맞는 동아리가 없습니다");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("데이터 베이스 오류");
        }
    }

}

