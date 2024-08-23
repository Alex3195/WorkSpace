package uz.alex.test_eimzo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alex.test_eimzo.model.MarkingCodeModel;

public interface MarkingCodeRepository extends JpaRepository<MarkingCodeModel, String> {
}
