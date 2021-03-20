package org.fiipractic.repository;

import org.fiipractic.config.ConnectionManager;
import org.fiipractic.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {

    ConnectionManager
    private List<Post> posts =  new ArrayList<>();

    public void addPost(Post post){
        posts.add(post);
    }
}
