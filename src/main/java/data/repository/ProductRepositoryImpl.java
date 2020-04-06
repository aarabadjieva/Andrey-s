package data.repository;

import data.entity.Product;
import service.models.ProductServiceModel;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private final EntityManager entityManager;

    @Inject
    public ProductRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("select p from Product p", Product.class)
                .getResultList();
    }

    @Override
    public void saveProduct(Product product) {
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }

    @Override
    public Product findByName(String productName) {
        return entityManager.createQuery("select p from Product p where p.name=:productName", Product.class)
                .setParameter("productName", productName)
                .getSingleResult();
    }

    @Override
    public void delete(Product product) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(product)?product:entityManager.merge(product));
        entityManager.getTransaction().commit();
    }
}
