package pard.hw5th.entity.Sheet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pard.hw5th.entity.Club.Club;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String place;

    private String time;
    private String day;


    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "club_id")
    @JsonIgnore
    private Club club;

    @Builder
    public Sheet(String place, String time, String day){
        this.place = place;
        this.time = time;
        this.day = day;
    }
    public boolean isChecked() {
        return club != null;
    }
}
