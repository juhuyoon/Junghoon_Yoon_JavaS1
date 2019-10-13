package com.trilogyed.post.controller;

import com.trilogyed.post.DAO.PostDao;
import com.trilogyed.post.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//ALL POST CONTROLLERS
@RestController
@RefreshScope
@RequestMapping("/posts")
public class PostController {
    @Qualifier("postDao")
    @Autowired
    PostDao postDao;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post) {
        return postDao.addPost(post);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post getPost(@PathVariable("id") int id) {
        Post returnPost = postDao.getPost(id);
        if(returnPost == null) {
            throw new IllegalArgumentException("No Post found with id : " + id);
        } else {
            return returnPost;
        }
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@RequestBody Post post, @PathVariable("id") int id) {
        post.setPostID(id);
        postDao.updatePost(post);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable("id") int id) {
        postDao.deletePost(id);
    }

    @GetMapping(value = "/{poster_name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPostsByPosterName(@PathVariable String posterName) {
        List<Post> postList = postDao.getPostsByPosterName(posterName);
        if (postList == null) {
            throw new IllegalArgumentException("Post does not exist for name " + posterName);
        }
        return postList;
    }
}
