package com.trilogyed.comment.DAO;

import com.trilogyed.comment.model.Comment;
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
public class CommentDaoTest {

    @Autowired
    private CommentDao commentDao;

    private Comment comment1, comment2;

    @Before
    public void setUp() throws Exception {
        commentDao.getAllComments().forEach(c -> {
            commentDao.deleteComment(c.getCommentId());
        });

        comment1 = new Comment();
        comment1.setCommentId(1);
        comment1.setPostId(1);
        comment1.setCreateDate(LocalDate.of(2015, 5, 15));
        comment1.setCommenterName("Jung");
        comment1.setComment("SOME COMMENT");

        comment2 = new Comment();
        comment2.setCommentId(2);
        comment2.setPostId(2);
        comment2.setCreateDate(LocalDate.of(2016, 10, 20));
        comment2.setCommenterName("OTHER");
        comment2.setComment("SOME OTHER COMMENT");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addGetDeleteComment() {
        comment1 = commentDao.addComment(comment1);
        assertEquals(1, commentDao.getAllComments().size());
        Comment fromDao = commentDao.getComment(comment1.getCommentId());
        assertEquals(comment1, fromDao);
        commentDao.deleteComment(comment1.getCommentId());
        fromDao = commentDao.getComment(comment1.getCommentId());
        assertNull(fromDao);
    }

    @Test
    public void getAllComments() {
        commentDao.addComment(comment1);
        commentDao.addComment(comment2);
        List<Comment> cList = commentDao.getAllComments();
        assertEquals(2, cList.size());
    }

    @Test
    public void updateComment() {
        commentDao.addComment(comment1);
        comment1.setCommenterName("ANOTHER PERSON");
        Comment fromDao = commentDao.getComment(comment1.getCommentId());

        assertNotEquals(comment1, fromDao);
    }

}