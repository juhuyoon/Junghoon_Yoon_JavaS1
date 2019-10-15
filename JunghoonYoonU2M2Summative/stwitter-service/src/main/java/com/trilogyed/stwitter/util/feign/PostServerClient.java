package com.trilogyed.stwitter.util.feign;

import com.trilogyed.stwitter.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Accessible Feign Client API from POST Server
@FeignClient("post-server-service")
public interface PostServerClient {

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post);

    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable("id") int id);

    @GetMapping("/posts/")
    public List<Post> getAllPosts();

    @PutMapping("/posts/{id}")
    public void updatePost(@RequestBody Post post, @PathVariable("id") int id);

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") int id);

    @GetMapping(value = "/posts/{poster_name}")
    public List<Post> getPostsByPosterName(@PathVariable("poster_name") String posterName);
}
