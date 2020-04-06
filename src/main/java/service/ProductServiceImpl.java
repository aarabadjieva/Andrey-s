package service;

import data.entity.Product;
import data.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import service.models.ProductServiceModel;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductServiceModel> findAllProducts() {
        return productRepository.findAll()
                .stream().map(p -> mapper.map(p, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void saveProduct(ProductServiceModel product) {
        productRepository.saveProduct(mapper.map(product, Product.class));
    }

    @Override
    public ProductServiceModel findByName(String productName) {
        return mapper.map(productRepository.findByName(productName), ProductServiceModel.class);
    }

    @Override
    public void deleteProduct(String productName) {
        ProductServiceModel product = findByName(productName);
        productRepository.delete(mapper.map(product, Product.class));
    }
}
