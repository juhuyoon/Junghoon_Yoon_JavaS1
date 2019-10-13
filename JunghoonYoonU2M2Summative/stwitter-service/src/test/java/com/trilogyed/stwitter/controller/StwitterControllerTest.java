package com.trilogyed.stwitter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.stwitter.ViewModel.PostViewModel;
import com.trilogyed.stwitter.servicelayer.ServiceLayer;
import com.trilogyed.stwitter.util.messages.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(StwitterController.class)
public class StwitterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ServiceLayer service;

    @MockBean
    RabbitTemplate rabbitTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createPost() throws Exception {
        PostViewModel pvm = new PostViewModel();
        pvm.setPostId(1);
        pvm.setPost("SOME POST");
        pvm.setPostDate(LocalDate.of(2015,5,15));
        pvm.setPosterName("Jung");

        Comment comment = new Comment();
        comment.setComment_content("comments");

        List<Comment> comments = new ArrayList<>();
        comments.add(comment);

        pvm.setComments(comments);

        String inputJson = mapper.writeValueAsString(pvm);

        PostViewModel pvm2 = new PostViewModel();
        pvm.setPost("NEW POST");
        pvm.setPostDate(LocalDate.of(2015,5,15));
        pvm.setPosterName("Jung2");
        pvm.setPostId(2);

        String outputJson = mapper.writeValueAsString(pvm2);

        when(service.addPost(pvm)).thenReturn(pvm2);

        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void getPost() throws Exception {
        PostViewModel pvm = new PostViewModel();
        pvm.setPostId(1);
        pvm.setPost("SOME POST");
        pvm.setPostDate(LocalDate.of(2015,5,15));
        pvm.setPosterName("Jung");

        Comment comment = new Comment();
        comment.setComment_content("comments");

        List<Comment> comments = new ArrayList<>();
        comments.add(comment);

        pvm.setComments(comments);

        String outputJson = mapper.writeValueAsString(pvm);
        when(service.getPost(1)).thenReturn(pvm);

        this.mockMvc.perform(get("/posts/post/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void getAllPostsByPoster() {

    }

    @Test
    public void createComment() {
    }

    @Test
    public void updateComment() {
    }
}