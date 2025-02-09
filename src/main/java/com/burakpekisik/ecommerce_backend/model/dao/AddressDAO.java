package com.burakpekisik.ecommerce_backend.model.dao;

import com.burakpekisik.ecommerce_backend.model.Address;
import com.burakpekisik.ecommerce_backend.model.LocalUser;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface AddressDAO extends ListCrudRepository<Address, Long> {

    List<Address> findByUser_Id(Long id);

    Long user(LocalUser user);
}
