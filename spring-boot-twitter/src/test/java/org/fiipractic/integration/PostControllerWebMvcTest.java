package org.fiipractic.integration;

import org.fiipractic.controller.PostController;
import org.fiipractic.model.Post;
import org.fiipractic.model.User;
import org.fiipractic.service.PostService;
import org.fiipractic.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest(PostController.class)
public class PostControllerWebMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostService service;

    @Autowired
    private UserService userService;

    @Test
    public void shouldSuccessfullyReturnAllPosts()
            throws Exception {

        User author = new User("userName","firstName", "lastName", "email@mail.test", "pass");
        author.setId(1L);

        userService.create(author);

        Post post1 = new Post();
        post1.setId(1L);
        post1.setAuthor(author);
        post1.setMessage("Message 1");


        Post post2 = new Post();
        post2.setId(1L);
        post2.setAuthor(author);
        post2.setMessage("Message 2");

        List<Post> allPosts = Arrays.asList(post1, post2);

        when(service.getAllPosts()).thenReturn(allPosts);

        mvc.perform(get("/posts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].message", is(post1.getMessage())))
                .andExpect(jsonPath("$[1].message", is(post2.getMessage())));
    }
}
