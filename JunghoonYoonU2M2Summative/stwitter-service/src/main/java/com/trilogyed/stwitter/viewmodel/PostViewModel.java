package com.trilogyed.stwitter.viewmodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.trilogyed.stwitter.util.messages.Comment;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class PostViewModel {
    private int postId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate postDate;
    @NotBlank(message = "Please input a valid name")
    private String posterName;
    @NotBlank(message = "Please input a valid name")
    private String post;
    @NotBlank(message = "Please ask for a list of comments")
    private List<Comment> comments;

    //Default Constructor
    public PostViewModel() {
    }

    // Constructor
    public PostViewModel(int postId, LocalDate postDate, String posterName, String post, List<Comment> comments) {
        this.postId = postId;
        this.postDate = postDate;
        this.posterName = posterName;
        this.post = post;
        this.comments = comments;
    }

    //Getters & Setters
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostViewModel that = (PostViewModel) o;
        return postId == that.postId &&
                postDate.equals(that.postDate) &&
                posterName.equals(that.posterName) &&
                post.equals(that.post) &&
                comments.equals(that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, postDate, posterName, post, comments);
    }

    @Override
    public String toString() {
        return "PostViewModel{" +
                "postId=" + postId +
                ", postDate=" + postDate +
                ", posterName='" + posterName + '\'' +
                ", post='" + post + '\'' +
                ", comments=" + comments +
                '}';
    }
}
