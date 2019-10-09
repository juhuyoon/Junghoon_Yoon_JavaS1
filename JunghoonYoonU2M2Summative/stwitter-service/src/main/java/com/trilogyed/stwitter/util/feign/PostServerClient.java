package com.trilogyed.stwitter.util.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("post-server")
public interface PostServerClient {

//    @GetMapping(value = "/post/{post_id}")
//    Post getPost


}
