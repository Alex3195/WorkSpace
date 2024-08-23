package uz.alex.test_eimzo.service.impl;

import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import uz.alex.test_eimzo.dto.ProductDto;
import uz.alex.test_eimzo.model.ProductModel;
import uz.alex.test_eimzo.repository.ProductModelRepository;
import uz.alex.test_eimzo.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductModelRepository productModelRepository;
    private ModelMapper modelMapper;

    public ProductServiceImpl(ProductModelRepository productModelRepository) {
        this.productModelRepository = productModelRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<ProductDto> getProducts() {
        return List.of();
    }

    @Override
    public ProductDto getProduct(String id) {
        return null;
    }

    @Override
    public ProductDto addProduct(ProductDto product) {
        try {
            ProductModel productModel = modelMapper.map(product, ProductModel.class);
            productModelRepository.save(productModel);
            return modelMapper.map(productModel, ProductDto.class);
        } catch (DataIntegrityViolationException ex) {
            Optional<ProductModel> model = productModelRepository.findByProductName(product.getProductName());
            return model.map(productModel -> modelMapper.map(productModel, ProductDto.class)).orElse(null);
        }
    }

    @Override
    public ProductDto updateProduct(ProductDto product) {
        return null;
    }

    @Override
    public void deleteProduct(String id) {

    }
}
