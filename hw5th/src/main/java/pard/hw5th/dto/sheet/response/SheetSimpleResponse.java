package pard.hw5th.dto.sheet.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pard.hw5th.entity.Sheet.Sheet;

@Getter
@NoArgsConstructor
public class SheetSimpleResponse {
    private Long id;
    private String place;
    private String time;
    private String day;

    public SheetSimpleResponse(Sheet sheet){
        this.id = sheet.getId();
        this.place = sheet.getPlace();
        this.time = sheet.getTime();
        this.day = sheet.getDay();
    }

}
