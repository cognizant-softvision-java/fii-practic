package integration;

import org.fiipractic.config.WebConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PostControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }


    @Test
    public void shouldFindPostControllerBeanInServletContext() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("postController"));
    }

    @Test
    public void shouldSuccessfullyCreatePost() throws Exception {
        this.mockMvc.perform(post("/user/registration").contentType(APPLICATION_FORM_URLENCODED_VALUE)
                .param("userName", "alex.atomei")
                .param("firstName", "Alex")
                .param("lastName", "Atomei")
                .param("email", "test@test.com")
                .param("pass", "pass"))
                .andDo(print()).andExpect(status().is(302))
                .andReturn();

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


    @Test
    public void shouldReturnAllPosts() throws Exception {
        this.mockMvc.perform(post("/user/registration").contentType(APPLICATION_FORM_URLENCODED_VALUE)
                .param("userName", "alex.atomei")
                .param("firstName", "Alex")
                .param("lastName", "Atomei")
                .param("email", "test@test.com")
                .param("pass", "pass"))
                .andDo(print()).andExpect(status().is(302))
                .andReturn();

        String createFirstPostRequest = "{\n" +
                "    \"message\": \"My tweet\",\n" +
                "    \"authorId\": 1" +
                "}";

        String createSecondPostRequest = "{\n" +
                "    \"message\": \"My second tweet\",\n" +
                "    \"authorId\": 1" +
                "}";

        this.mockMvc.perform(post("/posts").contentType("application/json").content(createFirstPostRequest))
                .andDo(print()).andExpect(status().is(200))
                .andReturn();

        this.mockMvc.perform(post("/posts").contentType("application/json").content(createSecondPostRequest))
                .andDo(print()).andExpect(status().is(200))
                .andReturn();


        this.mockMvc.perform(get("/posts"))
                .andDo(print()).andExpect(status().is(200))
                .andExpect(jsonPath("$[0].message").value("My tweet"))
                .andExpect(jsonPath("$[0].postId").value(1))
                .andExpect(jsonPath("$[0].author.id").value(1))
                .andExpect(jsonPath("$[1].message").value("My second tweet"))
                .andExpect(jsonPath("$[1].postId").value(2))
                .andExpect(jsonPath("$[1].author.id").value(1));
    }
}
