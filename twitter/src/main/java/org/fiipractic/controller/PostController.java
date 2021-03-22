package org.fiipractic.controller;

import org.fiipractic.dto.PostDTO;
import org.fiipractic.model.Post;
import org.fiipractic.service.PostService;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
=======
>>>>>>> 097509778aa3205f7c132c9e56aaef09d9843ba7
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> 097509778aa3205f7c132c9e56aaef09d9843ba7
@RestController
@RequestMapping("/posts")
public class PostController {

<<<<<<< HEAD
    @Autowired
    private PostService postService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Long createPost(@RequestBody PostDTO post) {
        return postService.createPost(post);
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
=======
    private PostService postService;

    public Long createPost(@RequestBody PostDTO post){

>>>>>>> 097509778aa3205f7c132c9e56aaef09d9843ba7
    }
}
