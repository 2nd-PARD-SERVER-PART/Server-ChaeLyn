package pard.hw5th.dto.sheet.request;

import lombok.Data;

@Data
public class SheetUpdateRequest {
    private String place;
    private String time;
    private String day;
}
