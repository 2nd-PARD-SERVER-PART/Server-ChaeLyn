package com.pard.hwSecurity.controller;

import com.pard.hwSecurity.dto.ResponseDto;
import com.pard.hwSecurity.dto.SignInDto;
import com.pard.hwSecurity.dto.SignInResponseDto;
import com.pard.hwSecurity.dto.SignUpDto;
import com.pard.hwSecurity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
public class SignUpController {
    private AuthService authService;

    @Autowired
    public SignUpController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/signUp")
    public ResponseDto<?> signUp(@RequestBody SignUpDto dto){
        ResponseDto<?> result = authService.signUp(dto);
        return result;
    }

    @PostMapping("/signIn")
    public ResponseDto<SignInResponseDto>signIn(@RequestBody SignInDto dto){
        ResponseDto<SignInResponseDto> result = authService.signIn(dto);
        return result;
    }
}
