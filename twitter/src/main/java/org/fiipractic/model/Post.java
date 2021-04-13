package org.fiipractic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "post")
public class Post {

    @JsonProperty("postId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "message")
    private String message;
    @Column(name = "user_id")
    private Long authorId;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private List<Reply> replies = new ArrayList<>();
    @ElementCollection
    //@CollectionTable(name = "post_like_mapping",
     //   joinColumns = {@JoinColumn(name = "post_id", referencedColumnName = "id")})
    //@MapKeyColumn(name = "liked_by_user")
    //@Column(name = "like")
    private Map<Long,Boolean> likes = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public Map<Long, Boolean> getLikes() {
        return likes;
    }

    public void setLikes(Map<Long, Boolean> likes) {
        this.likes = likes;
    }
}
