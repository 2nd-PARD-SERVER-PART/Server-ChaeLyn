package pard.hw5th.dto.club.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pard.hw5th.entity.Club.Club;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClubCreateRequest {
    private String name;
    private Integer num;

    public Club toEntity(){
        return Club.builder()
                .name(name)
                .num(num)
                .build();
    }
}
