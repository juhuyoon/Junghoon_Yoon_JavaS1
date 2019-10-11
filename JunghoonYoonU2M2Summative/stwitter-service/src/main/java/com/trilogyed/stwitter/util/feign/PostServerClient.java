package com.trilogyed.stwitter.util.feign;

import com.trilogyed.stwitter.util.messages.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("post-server-service")
public interface PostServerClient {

    @GetMapping("/posts/post/{id}")
    public Post getPost(@PathVariable("id") int id);

    @GetMapping("/posts/post")
    public List<Post> getAllPosts();

    @PostMapping("/posts/post")
    public Post createPost(@RequestBody Post post);

    @PutMapping("/posts/post/{id}")
    public void updatePost(@RequestBody Post post, @PathVariable("id") int id);

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") int id);


}
