package org.fiipractic.model;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Post {

    @JsonProperty("postId")
    private Long id;
    private String message;
    private User author;
    private long timestamp;
=======
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity(name = "post")
public class Post {

    private Long id;
    private String message;

    @OneToOne
    private User user;
    private long timeStamp;

    @OneToMany
>>>>>>> 097509778aa3205f7c132c9e56aaef09d9843ba7
    private List<Reply> replies;

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

<<<<<<< HEAD
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
=======
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
>>>>>>> 097509778aa3205f7c132c9e56aaef09d9843ba7
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }
}
