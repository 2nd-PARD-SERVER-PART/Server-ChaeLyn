package pard.hw5th.dto.club.response;

import lombok.Data;
import pard.hw5th.dto.sheet.response.SheetSimpleResponse;
import pard.hw5th.entity.Club.Club;

import java.util.List;
import java.util.stream.Collectors;



@Data
public class ClubResponse{
    private Long id;
    private String name;
    private Integer num;
    private List<SheetSimpleResponse> checkedSheets;

    public ClubResponse(Club club){
        this.id = club.getId();
        this.name = club.getName();
        this.num = club.getNum();

        if(club.getCheckedSheet() != null){
            this.checkedSheets = club.getCheckedSheet().stream()
                    .map(SheetSimpleResponse::new)
                    .collect(Collectors.toList());
        }
    }

}
