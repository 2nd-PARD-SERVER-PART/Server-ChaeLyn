package pard.thirdSeminar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {
    private String userEmail;
    private String userPassword;
}

//signup과 구분해라 왜냐면 up에는 시간도 포함...