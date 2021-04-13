package org.fiipractic.dto;

import org.fiipractic.model.Post;

public class ReplyDTO {

    private Long parentPostId;
    private boolean visible;
    private String message;

    public Long getParentPostId() {
        return parentPostId;
    }

    public void setParentPostId(Long parentPostId) {
        this.parentPostId = parentPostId;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
