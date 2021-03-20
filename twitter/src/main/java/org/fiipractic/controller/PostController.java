package org.fiipractic.controller;

import org.fiipractic.dto.PostDTO;
import org.fiipractic.model.Post;
import org.fiipractic.service.PostService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public Long createPost(@RequestBody PostDTO post){

    }
}
