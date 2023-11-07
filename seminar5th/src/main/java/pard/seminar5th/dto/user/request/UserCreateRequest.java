package pard.seminar5th.dto.user.request;

import lombok.Data;

@Data
public class UserCreateRequest {

    private String name;
    private Integer age;

}