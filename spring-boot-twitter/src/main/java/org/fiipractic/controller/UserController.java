package org.fiipractic.controller;

import org.fiipractic.model.FollowForm;
import org.fiipractic.model.Post;
import org.fiipractic.model.User;
import org.fiipractic.service.PostService;
import org.fiipractic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;
    /*
    @GetMapping("/")
    public String getAll(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("tableName", "List of all users:");
        model.addAttribute("users", users);
        return "list";
    }
    /*
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm) {
        userService.create(userForm);
        return "redirect:/user/";
    }
     */

    @GetMapping(value ="/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return userService.getAll();

    }

    @PostMapping(value ="/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long registration(@RequestBody User userFromRequest){
        return userService.create(userFromRequest);
    }

    /*
    @GetMapping("/{userName}")
    public String search(@PathVariable String userName, Model model) {
        User user = userService.findByUserName(userName);
        List<User> users = Collections.singletonList(user);
        model.addAttribute("tableName", "Details for user with userName: " + userName);
        model.addAttribute("users", users);
        return "list";
    }*/
    @GetMapping(value = "/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User search(@PathVariable String userName){
        return userService.findByUserName(userName);
    }

/*
    @GetMapping("/id/{sid}")
    public String searchById(@PathVariable String sid, Model model){
        Long id = Long.parseLong(sid);
        User user = userService.findById(id);
        List<User> users = Collections.singletonList(user);
        model.addAttribute("tableName", "Details for user with id: " + id);
        model.addAttribute("users", users);
        return "list";
    }*/
    @GetMapping(value = "/id/{sid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User searchById(@PathVariable String sid){
        Long id = Long.parseLong(sid);
        return userService.findById(id);
    }
    /*
    @GetMapping("/{userId}/follow")
    public String follow(@PathVariable Long userId, Model model) {
        model.addAttribute("followForm", new FollowForm());
        model.addAttribute("userId", userId);
        return "follow";
    }*/
    @PostMapping(value="/{userId}/follow", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long follow(@PathVariable Long userId, @RequestBody FollowForm followForm){

        return userService.addFolloweeForUser(userId, followForm.getFolloweeId());

    }

/*
    @PostMapping("/{userId}/follow")
    public String follow(@PathVariable Long userId, @ModelAttribute("userForm") FollowForm followForm){
        userService.addFolloweeForUser(userId, followForm.getFolloweeId());
        return "redirect:/user/";
    }*/

    @GetMapping(value = "/{userId}/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> getUserPosts(@PathVariable Long userId){
        return postService.getUserPosts(userId);
    }

    @GetMapping(value = "/{userId}/feed", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> getFeed(@PathVariable Long userId){
        List<Long> followeesIds = userService.getFolloweesForUser(userId);
        return followeesIds.stream()
                .flatMap(id -> postService.getUserPosts(id).stream())
                .collect(Collectors.toList());

    }

    @PostMapping(value = "/{userId}/like", produces = MediaType.APPLICATION_JSON_VALUE)
    public String likePost(@PathVariable Long userId, @RequestBody Long postId){
        return postService.likePost(userId, postId);
    }



}
