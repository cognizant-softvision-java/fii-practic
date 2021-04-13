package org.fiipractic.service;

import org.fiipractic.dto.PostDTO;
import org.fiipractic.dto.ReplyDTO;
import org.fiipractic.model.Post;
import org.fiipractic.model.Reply;
import org.fiipractic.model.User;
import org.fiipractic.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    public Long createPost(PostDTO postDTO) {

        Post post = new Post();
        //post.setId(postRepository.generatePostId());
        post.setMessage(postDTO.getMessage());
        post.setAuthorId(postDTO.getAuthorId());
        post.setTimestamp(java.time.LocalDateTime.now());

        //postRepository.addPost(post);
        postRepository.save(post);

        return post.getId();
    }

    public List<Post> getAllPosts() {
        List<Post> list = new ArrayList<>();
         postRepository.findAll()
                .forEach(list::add);
        return list;
    }

    public List<Post> getUserPosts(Long userId){
        return getAllPosts().stream()
                .filter(post -> post.getAuthorId().equals(userId))
                .collect(Collectors.toList());
    }

    public String likePost(Long userId, Long postId){
        Post post = postRepository.findById(postId).get();
        Map<Long,Boolean> postLikes = post.getLikes();
        postLikes.put(userId, true);
        post.setLikes(postLikes);
        postRepository.save(post);
        return "Post liked!";
    }

    public String addReplyToPost(ReplyDTO replyDTO){
        Post post = postRepository.findById(replyDTO.getParentPostId()).get();
        List<Reply> postReplies = post.getReplies();

        Reply reply = new Reply();
        reply.setMessage(replyDTO.getMessage());
        reply.setParentId(post.getId());
        reply.setVisible(replyDTO.isVisible());
        postReplies.add(reply);
        post.setReplies(postReplies);
        postRepository.save(post);

        return "Reply added to post with id: " + post.getId();

    }
}
