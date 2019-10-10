package com.trilogyed.commentqueueconsumer.util.feign;

import com.trilogyed.commentqueueconsumer.util.messages.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="comment-queue-service")
public interface CommentServiceClient {
    @PostMapping(value = "/comments")
    public Comment createComment(@RequestBody Comment comment);

    @PutMapping("/comments/{id}")
    public void updateComment(@RequestBody Comment comment, @PathVariable(name="id") int id);
}
