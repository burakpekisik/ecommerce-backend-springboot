package com.burakpekisik.ecommerce_backend.model.dao;

import com.burakpekisik.ecommerce_backend.model.LocalUser;
import com.burakpekisik.ecommerce_backend.model.WebOrder;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface WebOrderDAO extends ListCrudRepository<WebOrder, Long> {

    List<WebOrder> findByUser(LocalUser user);
}
