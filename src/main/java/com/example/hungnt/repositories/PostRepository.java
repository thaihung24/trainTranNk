package com.example.hungnt.repositories;

import com.example.hungnt.models.PostsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostsEntity,Long> {
}
