package org.fiipractic.controller;

import org.fiipractic.dto.PostDTO;
import org.fiipractic.dto.ReplyDTO;
import org.fiipractic.model.Post;
import org.fiipractic.model.Reply;
import org.fiipractic.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Long createPost(@RequestBody PostDTO post) {
        return postService.createPost(post);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping(value = "/reply", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createReply(@RequestBody ReplyDTO replyDTO){
        return postService.addReplyToPost(replyDTO);
    }
}
