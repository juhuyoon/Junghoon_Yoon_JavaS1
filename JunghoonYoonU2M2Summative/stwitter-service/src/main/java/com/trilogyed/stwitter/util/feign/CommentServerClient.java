package com.trilogyed.stwitter.util.feign;

import com.trilogyed.stwitter.util.messages.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "comment-server-service")
public interface CommentServerClient {
    @GetMapping("/comments/{id}")
    public Comment getComment(@PathVariable("id") int id);
    @GetMapping("/comments")
    public List<Comment> getAllComments();
    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable("id") int id);
}
