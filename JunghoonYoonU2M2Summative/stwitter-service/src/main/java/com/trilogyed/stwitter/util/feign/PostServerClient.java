package com.trilogyed.stwitter.util.feign;

import com.trilogyed.stwitter.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Accessible Feign Client API from POST Server
@FeignClient("post-server-service")
public interface PostServerClient {

    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable("id") int id);

    @GetMapping("/posts")
    public List<Post> getAllPosts();

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post);

    @PutMapping("/posts/{id}")
    public void updatePost(@RequestBody Post post, @PathVariable("id") int id);

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") int id);

    @RequestMapping(value = "/posts/{poster_name}", method = RequestMethod.GET)
    public List<Post> getPostsByPosterName(@PathVariable String posterName);
}
