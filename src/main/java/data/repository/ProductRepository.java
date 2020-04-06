package data.repository;

import data.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();

    void saveProduct(Product product);

    Product findByName(String productName);

    void delete(Product product);
}
