package org.fiipractic.integration;

import org.fiipractic.TwitterApplication;
import org.fiipractic.model.User;
import org.fiipractic.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = TwitterApplication.class)
@AutoConfigureMockMvc
public class PostControllerSpringBootTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    public void shouldSuccessfullyCreatePost() throws Exception {
        User author = new User("userName","firstName", "lastName", "email@mail.test", "pass");
        userService.create(author);

        String createPostRequest = "{\n" +
                "    \"message\": \"My tweet\",\n" +
                "    \"authorId\": 1" +
                "}";

        MvcResult mvcResult = this.mockMvc.perform(post("/posts").contentType("application/json").content(createPostRequest))
                .andDo(print()).andExpect(status().is(200))
                .andReturn();


        assertEquals("application/json",
                mvcResult.getResponse().getContentType());

        assertEquals("1", mvcResult.getResponse().getContentAsString());
    }
}
