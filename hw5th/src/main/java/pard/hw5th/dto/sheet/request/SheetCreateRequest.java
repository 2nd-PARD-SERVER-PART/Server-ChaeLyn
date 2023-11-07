package pard.hw5th.dto.sheet.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pard.hw5th.entity.Sheet.Sheet;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SheetCreateRequest {
    private String place;
    private String time;
    private String day;

    // builder 패턴을 사용하면, 순서에 상관없이 객체를 생성할 수 있다.
    public Sheet toEntity() {
        return Sheet.builder()
                .place(place)
                .time(time)
                .day(day)
                .build();
    }
}
