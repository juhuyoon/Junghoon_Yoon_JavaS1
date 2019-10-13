package com.trilogyed.stwitter.controller;


import com.trilogyed.stwitter.ViewModel.PostViewModel;
import com.trilogyed.stwitter.servicelayer.ServiceLayer;
import com.trilogyed.stwitter.util.messages.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@CacheConfig(cacheNames = {"posts"})
public class StwitterController {

    @Autowired
    private ServiceLayer service;

    /**
     * Creating the post Route with Cache put to prepare the data for return
     * @param pvm
     * @return
     */
    @CachePut(key = "#result.getPostId()")
    @PostMapping("/posts")
    public PostViewModel createPost(@RequestBody PostViewModel pvm) {
        pvm = service.addPost(pvm);
        return pvm;
    }

    /**
     * Retrieving the post and returned cached data
     * @param postId
     * @return
     */
    @Cacheable
    @GetMapping("/posts/{id}")
    public PostViewModel getPost(@PathVariable int postId) {
        return service.getPost(postId);
    }

    /**
     * Retrieving the post by the poster name and returning cached data
     * @param poster_name
     * @return
     */
    @Cacheable
    @GetMapping("posts/user/{poster_name}")
    public List<PostViewModel> getAllPostsByPoster(@PathVariable String poster_name) {
        return service.getAllPostsByPoster(poster_name);
    }

    @PostMapping("/comments/post/{post_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@PathVariable int post_id, @RequestBody @Valid Comment comment) {
        comment.setPostId(post_id);
        service.createComment(comment);
    }

    @PutMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateComment(@PathVariable int id, @RequestBody @Valid Comment comment) {
        comment.setCommentId(id);
        service.updateComment(comment);
    }


}
