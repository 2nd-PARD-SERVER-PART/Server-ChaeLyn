package pard.hw5th.repository.Club;

import org.springframework.data.jpa.repository.JpaRepository;
import pard.hw5th.entity.Club.Club;

import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long>{
    Optional<Club> findByName(String clubName);
    boolean existsByName(String clubName);
}
