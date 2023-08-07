package com.example.hungnt.repositories;

import com.example.hungnt.models.PostsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<PostsEntity,Long> {
    @Query("SELECT p FROM PostsEntity p JOIN p.customer c")
    List<PostsEntity>getPosts();
}
