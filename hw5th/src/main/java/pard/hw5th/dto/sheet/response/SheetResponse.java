package pard.hw5th.dto.sheet.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pard.hw5th.dto.club.response.ClubResponse;
import pard.hw5th.entity.Sheet.Sheet;

@Getter
@NoArgsConstructor
public class SheetResponse {
    private Long id;
    private String place;
    private String time;
    private String day;
    private ClubResponse club;

    public SheetResponse(Sheet sheet){
        this.id = sheet.getId();
        this.place = sheet.getPlace();
        this.time = sheet.getTime();
        this.day = sheet.getDay();

        if(sheet.getClub() != null){
            this.club = new ClubResponse(sheet.getClub());
        }
    }

}
