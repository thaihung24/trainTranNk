package com.example.hungnt.Services;

import com.example.hungnt.models.PostsEntity;
import com.example.hungnt.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    public List<PostsEntity>getPosts(){
        return  postRepository.getPosts();
    }
    public PostsEntity addPost(PostsEntity postsEntity){
        return postRepository.save(postsEntity);
    }
}
