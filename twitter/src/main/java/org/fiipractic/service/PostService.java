package org.fiipractic.service;

import org.fiipractic.dto.PostDTO;
import org.fiipractic.model.Post;
import org.fiipractic.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Long createPost(PostDTO post){
        postRepository.addPost(post);
    }
}
