package uz.alex.test_eimzo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alex.test_eimzo.model.AICModel;

import java.util.Optional;

public interface AICRepository extends JpaRepository<AICModel, String> {
    Optional<AICModel> findByCode(String code);
}
