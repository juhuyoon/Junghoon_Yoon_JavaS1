package com.trilogyed.stwitter.util.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "comment-server-service")
public interface CommentServerClient {
    @GetMapping
    getComment
    @GetMapping
    getAllComment
    @DeleteMapping
    deleteComment
}
