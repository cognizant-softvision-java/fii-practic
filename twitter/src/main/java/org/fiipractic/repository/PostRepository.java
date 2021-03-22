package org.fiipractic.repository;

<<<<<<< HEAD
=======
import org.fiipractic.config.ConnectionManager;
>>>>>>> 097509778aa3205f7c132c9e56aaef09d9843ba7
import org.fiipractic.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {

<<<<<<< HEAD
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
=======
    ConnectionManager
    private List<Post> posts =  new ArrayList<>();

    public void addPost(Post post){
        posts.add(post);
    }
>>>>>>> 097509778aa3205f7c132c9e56aaef09d9843ba7
}
