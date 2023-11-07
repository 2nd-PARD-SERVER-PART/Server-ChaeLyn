package pard.hw5th.entity.Club;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pard.hw5th.dto.club.request.ClubCreateRequest;
import pard.hw5th.entity.Sheet.Sheet;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private Integer num;

    @OneToMany(mappedBy = "club")
    private List<Sheet> checkedSheet = new ArrayList<>();

    @Builder
    public Club(String name, Integer num){
        this.name = name;
        this.num = num;
    }


    public void update(String name, Integer num) {
        if (name != null) {
            this.name = name;
        }

        if (num != null) {
            this.num = num;
        }
    }

}
