package com.pard.hwSecurity.service;

import com.pard.hwSecurity.config.WebSecurityConfig;
import com.pard.hwSecurity.dto.ResponseDto;
import com.pard.hwSecurity.dto.SignInDto;
import com.pard.hwSecurity.dto.SignInResponseDto;
import com.pard.hwSecurity.dto.SignUpDto;
import com.pard.hwSecurity.entity.UserEntity;
import com.pard.hwSecurity.repository.UserRepository;
import com.pard.hwSecurity.security.TokenProvider;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private UserRepository userRepository;
    private TokenProvider tokenProvider;
    private WebSecurityConfig webSecurityConfig;

    @Autowired
    public AuthService(UserRepository userRepository,TokenProvider tokenProvider, WebSecurityConfig webSecurityConfig){
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.webSecurityConfig = webSecurityConfig;
    }

    public ResponseDto<?> signUp(SignUpDto dto) {
        String userEmail = dto.getUserEmail();
        String userPassword = webSecurityConfig.getPasswordEncoder().encode(dto.getUserPassword());
        SignUpDto putDto = new SignUpDto(userEmail, userPassword);

        try {
            if (userRepository.existsById(userEmail)) {
                return ResponseDto.setFailed("이미 있는 아이디 입니다.");
            }
        } catch (Exception e) {
            return ResponseDto.setFailed("DB 에러");
        }

        UserEntity userEntity = new UserEntity(putDto);

        userRepository.save(userEntity);

        return ResponseDto.setSuccess("회원가입에 성공하였습니당 추카추카~!", userEntity);
    }

    public ResponseDto<SignInResponseDto> signIn(SignInDto dto){
        String userEmail = dto.getUserEmail();
        UserEntity user = userRepository.findById(userEmail).get();
        boolean passwordMatch = webSecurityConfig.getPasswordEncoder().matches(dto.getUserPassword(),user.getUserPassword());
        boolean existed = userRepository.existsById(userEmail)&&passwordMatch;
        if(!existed) return ResponseDto.setFailed("이메일이나 비밀번호를 다시 확인해주세요!");

        user.setUserPassword("");

        String token = tokenProvider.create(userEmail);
        int exprTime = 360000;
        SignInResponseDto signInResponseDto = new SignInResponseDto(token, exprTime, user);
        return ResponseDto.setSuccess("로그인에 성공하였습니둥!", signInResponseDto);
    }

}
