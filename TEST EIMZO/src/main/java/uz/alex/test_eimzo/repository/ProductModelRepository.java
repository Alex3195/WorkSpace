package uz.alex.test_eimzo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alex.test_eimzo.model.ProductModel;

import java.util.Optional;

public interface ProductModelRepository extends JpaRepository<ProductModel, String> {
    Optional<ProductModel> findByProductName(String productName);
}
