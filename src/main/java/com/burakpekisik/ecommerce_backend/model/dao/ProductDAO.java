package com.burakpekisik.ecommerce_backend.model.dao;

import com.burakpekisik.ecommerce_backend.model.Product;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductDAO extends ListCrudRepository<Product, Long> {

}
