package pard.thirdSeminar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pard.thirdSeminar.dto.SignUpDto;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //추가할 때마다 jpa가 알아서 늘여줌
    private int userNum;
    @Column(columnDefinition = "TEXT", nullable = false) //무조건 있어야돼
    private String userEmail; //mysql은 snake case 씀 user_email
    @Column(length = 20)
    private String userPassword; //user_password, default: VARCHAR(255)

    @CreationTimestamp
    private Timestamp userSignUpTime;
    public UserEntity(SignUpDto dto){
        this.userEmail = dto.getUserEmail();
        this.userPassword = dto.getUserPassword();
    }
}