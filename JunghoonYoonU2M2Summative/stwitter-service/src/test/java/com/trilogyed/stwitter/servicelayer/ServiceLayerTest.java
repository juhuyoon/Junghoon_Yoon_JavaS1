package com.trilogyed.stwitter.servicelayer;

import com.trilogyed.stwitter.ViewModel.PostViewModel;
import com.trilogyed.stwitter.model.Post;
import com.trilogyed.stwitter.util.feign.CommentServerClient;
import com.trilogyed.stwitter.util.feign.PostServerClient;
import com.trilogyed.stwitter.util.messages.Comment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ServiceLayerTest {

    @InjectMocks
    private ServiceLayer service;
    @Mock
    private CommentServerClient commentClient;
    @Mock
    private PostServerClient postClient;
    @Mock
    private RabbitTemplate rabbitTemplate;


    @Before
    public void setUp() throws Exception {
        setUpPostServerClientMock();
        setUpCommentServerClientMock();
        rabbitTemplate = mock(RabbitTemplate.class);

    }

    @Test
    public void getPost() {
        PostViewModel pvm = new PostViewModel();
        pvm.setPostId(1);
        pvm.setPost("SOME POST");
        pvm.setPostDate(LocalDate.of(2015,5,15));
        pvm.setPosterName("Jung");
        pvm.setComments(commentClient.getComment(pvm.getPostId()));

        PostViewModel tested = service.getPost(1);

        assertEquals(pvm, service.getPost(1));
    }

    @Test
    public void getAllPostsByPoster() {
        PostViewModel pvm = new PostViewModel();
        pvm.setPostId(1);
        pvm.setPost("SOME POST");
        pvm.setPostDate(LocalDate.of(2015,5,15));
        pvm.setPosterName("Jung");
        pvm.setComments(commentClient.getAllComments());

        Post expected = new Post();
        expected.setPostId(1);
        expected.setPostDate(LocalDate.of(2015, 5, 15));
        expected.setPosterName("Jung");
        expected.setPost("SOME POST");

        PostViewModel fromService = service.getPost(expected.getPostId());

        List<PostViewModel> pvmList = service.getAllPostsByPoster(pvm.getPosterName());

        assertEquals(1, pvmList.size());
    }

    @Test
    public void addPost() {
        PostViewModel pvm = new PostViewModel();
        pvm.setPostId(1);
        pvm.setPost("SOME POST");
        pvm.setPostDate(LocalDate.of(2015,5,15));
        pvm.setPosterName("Jung");
        pvm.setComments(commentClient.getComment(pvm.getPostId()));

        Post expected = new Post();
        expected.setPostId(1);
        expected.setPostDate(LocalDate.of(2015, 5, 15));
        expected.setPosterName("Jung");
        expected.setPost("SOME POST");

        PostViewModel fromService = service.getPost(expected.getPostId());

        assertEquals(pvm, fromService);
    }


    private void setUpCommentServerClientMock() {
        Comment expected = new Comment();
        expected.setCommentId(1);
        expected.setPostId(1);
        expected.setCommenterName("Jung");
        expected.setCommentDate(LocalDate.of(2015,5,15));
        expected.setComment_content("SOME COMMENT");

        doReturn(expected).when(commentClient).getComment(expected.getCommentId());

    }

    private void setUpPostServerClientMock() {
        Post expected = new Post();
        expected.setPostId(1);
        expected.setPostDate(LocalDate.of(2015, 5, 15));
        expected.setPosterName("Jung");
        expected.setPost("SOME POST");

        doReturn(expected).when(postClient).getPost(expected.getPostId());
    }


    @After
    public void tearDown() throws Exception {
    }

}