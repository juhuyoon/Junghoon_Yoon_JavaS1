package com.trilogyed.post.DAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostDaoTest {

    @Autowired
    private PostDao postDao;

    private Post post1, post2;

    @Before
    public void setUp() throws Exception {
        postDao.getAllPosts().forEach(p ->
        {
            postDao.deletePost(p.getPostID());
        });

        post1 = new Post();
        post1.setPostDate(LocalDate.of(2019, 5, 15));
        post1.setPosterName("Jung");
        post1.setPost("some post");

        post2 = new Post();
        post2.setPostDate(LocalDate.of(2018, 12, 5));
        post2.setPosterName("Someone");
        post2.setPost("OTHER post");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeletePost() {
        post1 = postDao.addPost(post1);
        assertEquals(1, postDao.getAllPosts().size());
        Post fromDao = postDao.getPost(post1.getPostID());
        assertEquals(post1, fromDao);
        postDao.deletePost(post1.getPostID());
        fromDao = postDao.getPost(post1.getPostID());
        assertNull(fromDao);

    }

    @Test
    public void getAllPosts() {
        postDao.addPost(post1);
        postDao.addPost(post2);
        List<Post> pList = postDao.getAllPosts();
        assertEquals(2, pList.size());
    }


    @Test
    public void updatePost() {
        postDao.addPost(post1);
        post1.setPosterName("ANOTHER PERSON");
        Post fromDao = postDao.getPost(post1.getPostID());

        assertNotEquals(post1, fromDao);
    }
}