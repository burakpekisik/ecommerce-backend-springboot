package com.burakpekisik.ecommerce_backend.service;

import com.burakpekisik.ecommerce_backend.model.Inventory;
import com.burakpekisik.ecommerce_backend.model.Product;
import com.burakpekisik.ecommerce_backend.model.dao.ProductDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getProducts() {
        return productDAO.findAll();
    }

    @Transactional
    public Product createProduct(Product product) {
        Inventory inventory = new Inventory();
        inventory.setQuantity(0);
        inventory.setProduct(product);
        product.setInventory(inventory);
        return productDAO.save(product);
    }

    @Transactional
    public Product updateProduct(Product product) {
        if (productDAO.existsById(product.getId())) {
            return productDAO.save(product);
        }
        return null;
    }

    @Transactional
    public Product updateInventory(Long productId, Integer quantity) {
        Optional<Product> optionalProduct = productDAO.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.getInventory().setQuantity(quantity);
            return productDAO.save(product);
        }
        return null;
    }
}