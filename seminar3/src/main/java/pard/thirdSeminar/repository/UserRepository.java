package pard.thirdSeminar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pard.thirdSeminar.entity.UserEntity;

import java.util.List;

//무조건 인터페이스로 생성, 무조건 integer
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserEmailAndUserPassword(String userEmail, String userPassword);
    //List<UserEntity> findByUserSignUpTimeOrderByUserSignUpTime //중요하다....짱길죠?
}
