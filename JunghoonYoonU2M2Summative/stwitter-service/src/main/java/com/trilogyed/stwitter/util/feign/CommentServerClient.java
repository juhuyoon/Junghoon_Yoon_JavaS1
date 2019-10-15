package com.trilogyed.stwitter.util.feign;

        import com.trilogyed.stwitter.util.messages.Comment;
        import org.springframework.cloud.openfeign.FeignClient;
        import org.springframework.web.bind.annotation.DeleteMapping;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;

        import java.util.List;

// Accessible API from the Comment Server
@FeignClient(name = "comment-server-service")
public interface CommentServerClient {
    @GetMapping("/comments/{id}")
    public List<Comment> getComment(@PathVariable("id") int id);
    @GetMapping("/comments")
    public List<Comment> getAllComments();
    @GetMapping("/comments/post/{post_id}")
    public List<Comment> getCommentsByPostId(@PathVariable("post_id") int post_id);
    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable("id") int id);
}
