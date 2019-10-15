package com.trilogyed.stwitter.controller;


import com.trilogyed.stwitter.servicelayer.ServiceLayer;
import com.trilogyed.stwitter.util.messages.Comment;
import com.trilogyed.stwitter.viewmodel.PostViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RefreshScope
//@CacheConfig(cacheNames = {"posts"})
public class StwitterController {

    @Autowired
    ServiceLayer service;

    /**
     * Creating the post Route with Cache put to prepare the data for return
     * @param pvm
     * @return
     */
    //@CachePut(key = "#result.getPostId()")
    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostViewModel createPost(@RequestBody PostViewModel pvm) {
        return service.addPost(pvm);
    }

    /**
     * Retrieving the post and returned cached data
     * @param postId
     * @return
     */
    //@Cacheable
    @GetMapping("/posts/{id}")
    public PostViewModel getPost(@PathVariable("id") int postId) {
        PostViewModel post = service.getPost(postId);
        if(post == null) {
            throw new IllegalArgumentException("Post does not exist for that post id" + postId);
        }
         return post;
    }

    /**
     * Retrieving the post by the poster name and returning cached data
     * @param poster_name
     * @return
     */
    //@Cacheable
    @GetMapping("posts/user/{poster_name}")
    public List<PostViewModel> getAllPostsByPoster(@PathVariable("poster_name") String poster_name) {
        List<PostViewModel> pvmList = service.getAllPostsByPoster(poster_name);
        if(pvmList == null) {
            throw new IllegalArgumentException("Post does not exist for that poster name " + poster_name);
        }
        return pvmList;
    }

    @PostMapping("/comments/post/{post_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@PathVariable("post_id") int post_id, @RequestBody @Valid Comment comment) {
        comment.setPostId(post_id);
        service.createComment(comment);
    }

    @PutMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateComment(@PathVariable("id") int id, @RequestBody @Valid Comment comment) {
        comment.setCommentId(id);
        service.updateComment(comment);
    }


}
