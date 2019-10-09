package com.trilogyed.post.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.post.DAO.PostDao;
import com.trilogyed.post.model.Post;
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
@WebMvcTest(PostController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Qualifier("postDao")
    @MockBean
    private PostDao postDao;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createPostShouldReturnSavedPost() {
        //Simulate Post object from the controller
        Post inputPost = new Post();
        inputPost.setPostID(1);
        inputPost.setPostDate(LocalDate.of(2019, 5, 15));
        inputPost.setPosterName("Jung");
        inputPost.setPost("some post");

        //Object to JSON in String
        String inputJson;
        try {
            inputJson = mapper.writeValueAsString(inputPost);
        } catch (JsonProcessingException e){
            throw new IllegalArgumentException("Cannot Convert to JSON");
        }

        //Simulated Post object from the controller
        Post outputPost = new Post();
        outputPost.setPostID(1);
        outputPost.setPostDate(LocalDate.of(2019, 5, 15));
        outputPost.setPosterName("Jung");
        outputPost.setPost("some post");

        //Object to JSON in String
        String outputJson;
        try {
            outputJson = mapper.writeValueAsString(outputPost);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert to JSON");
        }
    }

    @Test
    public void getPostByIdShouldReturnNoteWithId() {
        //Simulated Post object from the controller
        Post outputPost = new Post();
        outputPost.setPostID(1);
        outputPost.setPostDate(LocalDate.of(2019, 5, 15));
        outputPost.setPosterName("Jung");
        outputPost.setPost("some post");

        String outputJson;
        try {
            outputJson = mapper.writeValueAsString(outputPost);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert to JSON");
        }

        //Mocking DAO Response
        when(postDao.getPost(1)).thenReturn(outputPost);

        try {
            this.mockMvc.perform(get("/posts/" + outputPost.getPostID()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(outputJson));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPostThatDoesNotExistShouldReturn404() {
        int idForPostThatDoesNotExist = 100;

        when(postDao.getPost(idForPostThatDoesNotExist)).thenReturn(null);

        try {
            this.mockMvc.perform(get("/posts/" + idForPostThatDoesNotExist))
                        .andDo(print())
                        .andExpect(status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllPostsShouldReturnListOfPosts() {
        Post outputPost1 = new Post();
        outputPost1.setPostDate(LocalDate.of(2019, 5, 15));
        outputPost1.setPosterName("Jung");
        outputPost1.setPost("some post");
        outputPost1.setPostID(1);

        Post outputPost2 = new Post();
        outputPost2.setPostDate(LocalDate.of(2019, 5, 15));
        outputPost2.setPosterName("Jung2");
        outputPost2.setPost("some post2");
        outputPost2.setPostID(2);

        List<Post> pList = new ArrayList<>();
        pList.add(outputPost1);
        pList.add(outputPost2);

        when(postDao.getAllPosts()).thenReturn(pList);
        List<Post> listChecker = new ArrayList<>();
        listChecker.addAll(pList);

        String outputJson;
        try {
            outputJson = mapper.writeValueAsString(listChecker);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert to JSON");
        }

        try {
            this.mockMvc.perform(get("/posts"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().json(outputJson));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatePostIsOk() {
        Post inputPost = new Post();
        inputPost.setPostID(1);
        inputPost.setPostDate(LocalDate.of(2019, 5, 15));
        inputPost.setPosterName("Jung");
        inputPost.setPost("some post");

        String inputJson;
        try {
            inputJson = mapper.writeValueAsString(inputPost);
        } catch (JsonProcessingException e){
            throw new IllegalArgumentException("Cannot convert to JSON");
        }

        try {
            this.mockMvc.perform(MockMvcRequestBuilders.put("/posts/1")
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
    public void deletePostIsOk() {
        try {
            this.mockMvc.perform(MockMvcRequestBuilders.delete("/posts/1"))
                        .andDo(print()).andExpect(status().isOk())
                        .andExpect(content().string(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}