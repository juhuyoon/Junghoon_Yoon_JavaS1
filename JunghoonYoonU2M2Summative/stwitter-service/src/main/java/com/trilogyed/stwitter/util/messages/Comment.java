package com.trilogyed.stwitter.util.messages;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

// To send the Comment Object as a message for the Queue
public class Comment {
    private int commentId;
    @NotBlank(message = "please input a value")
    private int postId;
    @NotBlank(message = "please input a value")
    private String commenterName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate commentDate;
    @NotBlank(message = "please input a value")
    private String comment_content;

    public Comment() {
    }

    public Comment(int commentId, @NotBlank(message = "please input a value") int postId, @NotBlank(message = "please input a value") String commenterName, LocalDate commentDate, @NotBlank(message = "please input a value") String comment_content) {
        this.commentId = commentId;
        this.postId = postId;
        this.commenterName = commenterName;
        this.commentDate = commentDate;
        this.comment_content = comment_content;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public LocalDate getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentId == comment.commentId &&
                postId == comment.postId &&
                commenterName.equals(comment.commenterName) &&
                commentDate.equals(comment.commentDate) &&
                comment_content.equals(comment.comment_content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, postId, commenterName, commentDate, comment_content);
    }
}
