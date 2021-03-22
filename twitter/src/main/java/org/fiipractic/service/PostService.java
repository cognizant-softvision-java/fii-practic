package org.fiipractic.service;

import org.fiipractic.dto.PostDTO;
import org.fiipractic.model.Post;
import org.fiipractic.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> 097509778aa3205f7c132c9e56aaef09d9843ba7
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

<<<<<<< HEAD
    @Autowired
    private UserService userService;

    public Long createPost(PostDTO postDTO) {

        Post post = new Post();
        post.setId(postRepository.generatePostId());
        post.setMessage(postDTO.getMessage());
        post.setAuthor(userService.findById(postDTO.getAuthorId()));
        post.setTimestamp(System.currentTimeMillis());

        postRepository.addPost(post);

        return post.getId();
    }

    public List<Post> getAllPosts() {
        return postRepository.getPosts();
=======
    public Long createPost(PostDTO post){
        postRepository.addPost(post);
>>>>>>> 097509778aa3205f7c132c9e56aaef09d9843ba7
    }
}
