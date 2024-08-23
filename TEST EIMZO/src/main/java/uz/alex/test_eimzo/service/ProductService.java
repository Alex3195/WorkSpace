package uz.alex.test_eimzo.service;

import uz.alex.test_eimzo.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getProducts();

    ProductDto getProduct(String id);

    ProductDto addProduct(ProductDto product);

    ProductDto updateProduct(ProductDto product);

    void deleteProduct(String id);
}
