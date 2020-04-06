package service;

import service.models.ProductServiceModel;

import java.util.List;

public interface ProductService {
    List<ProductServiceModel> findAllProducts();

    void saveProduct(ProductServiceModel product);

    ProductServiceModel findByName(String productName);

    void deleteProduct(String productName);
}
