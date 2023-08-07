package com.example.hungnt.repositories;

import com.example.hungnt.models.CustomersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomersEntity,Long> {
    public CustomersEntity findCustomersEntitiesByEmail(String email);
}
