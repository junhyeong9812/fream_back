package com.kream.root.admin.repository;

import com.kream.root.MainAndShop.domain.Product;
import com.kream.root.MainAndShop.domain.ProductImg;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AdminProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Product saveProduct(Product product) {
        if (product.getPrid() == null) {
            entityManager.persist(product);
            return product;
        } else {
            return entityManager.merge(product);
        }
    }

    @Transactional
    public void saveProductImages(List<ProductImg> productImgs) {
        for (ProductImg productImg : productImgs) {
            if (productImg.getPriid() == null) {
                entityManager.persist(productImg);
            } else {
                entityManager.merge(productImg);
            }
        }
    }

    public Product findProductById(Long id) {
        return entityManager.find(Product.class, id);
    }

    public List<Product> findAllProducts() {
//        return entityManager.createQuery("SELECT p FROM Product p ORDER BY p.prid ASC", Product.class).getResultList();
        List<Product> products = entityManager.createQuery("SELECT p FROM Product p ORDER BY p.prid ASC", Product.class).getResultList();
        System.out.println("Number of products retrieved: " + products.size());
        return products;
    }

    @Transactional
    public void deleteProductById(Long id) {
        Product product = findProductById(id);
        if (product != null) {
            entityManager.remove(product);
        }
    }
}
