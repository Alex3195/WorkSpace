package uz.alex.test_eimzo.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductDto {
    private String uuid;
    private String productName;
    private Integer productGroupId;
    private String productGroup;
    private String brand;
}
