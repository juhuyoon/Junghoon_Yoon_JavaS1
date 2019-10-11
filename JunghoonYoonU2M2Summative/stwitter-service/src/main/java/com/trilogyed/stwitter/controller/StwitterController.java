package com.trilogyed.stwitter.controller;


import com.trilogyed.stwitter.ViewModel.PostViewModel;
import com.trilogyed.stwitter.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
@RequestMapping(value = "/posts")
public class StwitterController {

    @Autowired
    ServiceLayer service;

    @PostMapping("")
    public PostViewModel createPost(@RequestBody PostViewModel pvm) {
        //pvm = service;
        return null;
    }

    @GetMapping("/{id}")
    public PostViewModel getPost(@PathVariable int postId) {
        return null;
    }

    @GetMapping("/user/{poster_name}")
    public PostViewModel getAllPostsByPoster(@PathVariable String posterName) {
        return null;
    }


}
