package pard.thirdSeminar.service;

import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pard.thirdSeminar.dto.ResponseDto;
import pard.thirdSeminar.dto.SignInDto;
import pard.thirdSeminar.dto.SignUpDto;
import pard.thirdSeminar.entity.UserEntity;
import pard.thirdSeminar.repository.UserRepository;

import java.util.List;

@Service

public class UserService {
    private final UserRepository userRepository;
    //무한 루프 방지를 위해 autowired뒤에 쓰는 것
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public ResponseDto<UserEntity> signUp(SignUpDto dto){
        UserEntity user = new UserEntity(dto);
        String userEmail = dto.getUserEmail();
        try{
            if(userRepository.existsByUserEmail(userEmail)){
                return ResponseDto.setFailed("이미 존재하는 ID입니다");
            }
            userRepository.save(user); //create
            return ResponseDto.setSuccess("축하해", user); //세개 다 보임
        }catch(Exception e){
            e.printStackTrace(); //나 뭘 틀렸는지 아려주셈
            return ResponseDto.setFailed("데이터 베이스 오류");
        }
    }

    public ResponseDto<List<UserEntity>> findAll(){
        List<UserEntity> users;
        try {
            users = userRepository.findAll();
            return ResponseDto.setSuccess("find success",users);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("db오류");
        }
    }

    public ResponseDto<UserEntity> findOne(Integer userNum){
        UserEntity user;
        try{
            user = userRepository.findById(userNum).get();
            return ResponseDto.setSuccess("find One", user);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("db오류");
        }
    }

    //db 바뀌는 순간 알아서 저장 해줌, 굳이 save 안 넣어도 됨, 묶인게 하나의 작업 단위가 됨, 이거 안에 뭔가 실패하면 다 실패 되는거, 송금 초기화
    @Transactional
    public ResponseDto<UserEntity> update(Integer userNum, SignUpDto dto){
        UserEntity user;
        try{
            user = userRepository.findById(userNum).get();
            if(dto.getUserEmail() != null && !dto.getUserEmail().isEmpty())user.setUserEmail(dto.getUserEmail()); //userEmail 값 안 ㅂ냈을 때, 그대로 유지할 수 있도록 해주는 것
            if(dto.getUserPassword() != null && !dto.getUserPassword().isEmpty())user.setUserPassword(dto.getUserPassword());
            return ResponseDto.setSuccess("update success", user);
        }catch(Exception e) {
            return ResponseDto.setFailed("DB 오류");
        }
    }

    public ResponseDto<?> delete(Integer userNum){
        try{
            if(userRepository.existsById(userNum)){
                userRepository.deleteById(userNum);
                return ResponseDto.setSuccess("delete success", null);
            }
            return ResponseDto.setFailed("존재하지 않는 userID입니다.");
        }catch (Exception e){
            return ResponseDto.setFailed("DB 오류");
        }
    }

    public ResponseDto<String> signIn(SignInDto dto){
        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();
        boolean existed = userRepository.existsByUserEmailAndUserPassword(userEmail, userPassword);
        if(!existed) return ResponseDto.setFailed("없음");
        return ResponseDto.setSuccess("로그인 완료", userEmail);
        //try catch 쓰는게 좋음
    }
}


//repo 서랍장에서 가져오라고 시킬라고
