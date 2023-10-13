package pard.rlacofls.hw3rd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "set") // staticName = "set" 이걸 써야 set method 쓸 수 있답니당
public class ResponseDto<D> {
    private boolean result;
    private String message;//성공 실패 메세지
    private D data; //data 넣을거임

    public static <D> ResponseDto<D> setSuccess(String message, D data){
        return ResponseDto.set(true, message, data);
    }

    public static <D> ResponseDto<D> setFailed(String message){
        return ResponseDto.set(false, message, null);
    }
}

