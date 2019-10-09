package com.trilogyed.comment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.comment.DAO.CommentDao;
import com.trilogyed.comment.model.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CommentController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Qualifier("commentDao")
    @MockBean
    private CommentDao commentDao;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createCommentShouldReturnSavedPost() {
        Comment inputComment = new Comment();
        inputComment.setCommentId(1);
        inputComment.setPostId(1);
        inputComment.setCreateDate(LocalDate.of(2015, 5, 15));
        inputComment.setCommenterName("Jung");
        inputComment.setComment("SOME COMMENT");

        //Object to JSOn
        String inputJson;
        try {
            inputJson = mapper.writeValueAsString(inputComment);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot Convert to JSON");
        }

        //Simulated Comment object from the controller
        Comment outputComment = new Comment();
        outputComment.setCommentId(1);
        outputComment.setPostId(1);
        outputComment.setCreateDate(LocalDate.of(2015, 5, 15));
        outputComment.setCommenterName("Jung");
        outputComment.setComment("SOME COMMENT");

        //Object to JSON in String
        String outputJson;
        try {
            outputJson = mapper.writeValueAsString(outputComment);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot Convert to JSON");
        }

    }

    @Test
    public void getCommentByIdShouldReturnCommentWithId() {
        //Simulated Comment object from the controller
        Comment outputComment = new Comment();
        outputComment.setCommentId(1);
        outputComment.setPostId(1);
        outputComment.setCreateDate(LocalDate.of(2015, 5, 15));
        outputComment.setCommenterName("Jung");
        outputComment.setComment("SOME COMMENT");

        String outputJson;
        try {
            outputJson = mapper.writeValueAsString(outputComment);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert to JSON");
        }

        when(commentDao.getComment(1)).thenReturn(outputComment);
        try {
            this.mockMvc.perform(get("/comments/" + outputComment.getCommentId()))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().json(outputJson));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getCommentThatDoesNotExistShouldReturn404() {
        int idForCommentThatDoesNotExist = 400;

        when(commentDao.getComment(idForCommentThatDoesNotExist)).thenReturn(null);

        try {
            this.mockMvc.perform(get("/comments/" + idForCommentThatDoesNotExist))
                        .andDo(print())
                        .andExpect(status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllCommentsShouldReturnListOfPosts() {
        Comment outputComment = new Comment();
        outputComment.setPostId(1);
        outputComment.setCreateDate(LocalDate.of(2015, 5, 15));
        outputComment.setCommenterName("Jung");
        outputComment.setComment("SOME COMMENT");
        outputComment.setCommentId(1);

        Comment outputComment2 = new Comment();
        outputComment2.setPostId(2);
        outputComment2.setCreateDate(LocalDate.of(2015, 5, 15));
        outputComment2.setCommenterName("Jung2");
        outputComment2.setComment("SOME COMMENT2");
        outputComment2.setCommentId(2);

        List<Comment> cList = new ArrayList<>();
        cList.add(outputComment);
        cList.add(outputComment2);

        when(commentDao.getAllComments()).thenReturn(cList);
        List<Comment> listChecker = new ArrayList<>();
        listChecker.addAll(cList);

        String outputJson;
        try {
            outputJson = mapper.writeValueAsString(listChecker);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot conver to JSON");
        }

        try {
            this.mockMvc.perform(get("/comments"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().json(outputJson));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void updateCommentIsOk() {
        Comment inputComment = new Comment();
        inputComment.setPostId(1);
        inputComment.setCreateDate(LocalDate.of(2015, 5, 15));
        inputComment.setCommenterName("Jung");
        inputComment.setComment("SOME COMMENT");
        inputComment.setCommentId(1);

        String inputJson;
        try {
            inputJson = mapper.writeValueAsString(inputComment);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert to JSON");
        }

        try{
            this.mockMvc.perform(MockMvcRequestBuilders.put("/comments/1")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().string(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteCommentIsOk() {
        try {
            this.mockMvc.perform(MockMvcRequestBuilders.delete("/comments/1"))
                        .andDo(print()).andExpect(status().isOk())
                        .andExpect(content().string(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}