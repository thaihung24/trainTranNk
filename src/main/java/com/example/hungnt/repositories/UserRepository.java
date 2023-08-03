package com.example.hungnt.repositories;

import com.example.hungnt.models.UsersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UsersEntity,Long> {
    @Query("SELECT p FROM UsersEntity p WHERE p.email LIKE %?1%")
    public List<UsersEntity> search(String keyword);
}
