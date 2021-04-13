package org.fiipractic.repository;

import org.fiipractic.exception.NotFoundException;
import org.fiipractic.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
/*
    private List<Post> posts = new ArrayList<>();

    public void addPost(Post post) {
        posts.add(post);
    }

    public Long generatePostId() {
        return posts.size() + 1l;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Post getSpecificPost(Long postId){
        return posts.stream()
                .filter(post -> post.getId().equals(postId))
                .findFirst()
                .orElseThrow(()-> new NotFoundException("post", postId));
    }*/

}
