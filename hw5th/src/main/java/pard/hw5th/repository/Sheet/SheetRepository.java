package pard.hw5th.repository.Sheet;

import org.springframework.data.jpa.repository.JpaRepository;
import pard.hw5th.entity.Sheet.Sheet;

import java.util.List;
import java.util.Optional;

public interface SheetRepository extends JpaRepository<Sheet, Long> {
    List<Sheet> findByPlace(String place);
}
