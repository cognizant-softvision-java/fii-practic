package org.fiipractic.unit;

import org.fiipractic.dto.PostDTO;
import org.fiipractic.exception.NotFoundException;
import org.fiipractic.model.Post;
import org.fiipractic.model.User;
import org.fiipractic.repository.PostRepository;
import org.fiipractic.service.PostService;
import org.fiipractic.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {


    @InjectMocks
    private PostService postService;

    @Spy
    private PostRepository postRepository = new PostRepository();

    @Spy
    private UserService userService = new UserService();

    private static User author;
    private static PostDTO postDTO;

    @BeforeAll
    public static void setUp() {
        author = new User("userName","firstName", "lastName", "email@mail.test", "pass");
        author.setId(1L);

        postDTO = new PostDTO();
        postDTO.setAuthorId(1L);
        postDTO.setMessage("Message");
    }

    @Test
    public void shouldCreatePost() {
        doReturn(author).when(userService).findById(1L);

        Long postId = postService.createPost(postDTO);

        assertEquals(1L, postId);
    }

    @Test
    public void shouldThrowExceptionWhenAuthorCanNotBeFound() {
        Throwable exception = assertThrows(NotFoundException.class, () -> postService.createPost(postDTO));
        assertEquals("Entity user not found for id: 1", exception.getMessage());
    }

    @Test
    public void shouldReturnAllCreatedPosts() {
        PostDTO postDTO1 = new PostDTO();
        postDTO1.setAuthorId(1L);
        postDTO1.setMessage("Message");

        doReturn(author).when(userService).findById(1L);

        postService.createPost(postDTO);
        postService.createPost(postDTO1);

        List<Post> posts = postService.getAllPosts();

        assertEquals(2, posts.size());
        posts.forEach(p -> {
            assertEquals("Message", p.getMessage());
            assertEquals(1L, p.getAuthor().getId());
            assertEquals("userName", p.getAuthor().getUserName());
        });
    }
}
