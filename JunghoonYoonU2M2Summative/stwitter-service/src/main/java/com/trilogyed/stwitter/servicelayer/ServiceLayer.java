package com.trilogyed.stwitter.servicelayer;

import com.trilogyed.stwitter.ViewModel.PostViewModel;
import com.trilogyed.stwitter.model.Post;
import com.trilogyed.stwitter.util.feign.CommentServerClient;
import com.trilogyed.stwitter.util.feign.PostServerClient;
import com.trilogyed.stwitter.util.messages.Comment;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {

    CommentServerClient commentClient;
    PostServerClient postClient;

    ServiceLayer service;

    public static final String EXCHANGE = "comment-exchange";
    public static final String ROUTING_KEY = "comment.service.controller";

    private RabbitTemplate rabbitTemplate;

    public ServiceLayer() {
    }

    @Autowired
    public ServiceLayer(CommentServerClient commentClient, PostServerClient postClient, RabbitTemplate rabbitTemplate) {
        this.commentClient = commentClient;
        this.postClient = postClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * To create the comment that is getting sent to the queue
     * @param comment
     * @return
     */
    @PostMapping("/comments")
    public Comment createComment(@RequestBody @Valid Comment comment) {
        Comment msg = new Comment();
        msg.setCommentId(0);
        msg.setPostId(comment.getPostId());
        msg.setCommentDate(comment.getCommentDate());
        msg.setCommenterName(comment.getCommenterName());
        msg.setComment_content(comment.getComment_content());
        System.out.println("SENDING COMMENT");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, msg);
        System.out.println("SENT COMMENT");

        return msg;
    }

    /**
     *
     * @param comment
     */
    @PutMapping("/comments/{comment_id}")
    public void updateComment(@RequestBody @Valid Comment comment){
        Comment msg = new Comment();
        msg.setCommentId(comment.getCommentId());
        msg.setPostId(comment.getPostId());
        msg.setCommentDate(comment.getCommentDate());
        msg.setCommenterName(comment.getCommenterName());
        msg.setComment_content(comment.getComment_content());

        System.out.println("SENDING COMMENT");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, comment);
        System.out.println("UPDATED COMMENT");
    }

    /**
     * Getting post through post client.
     * @param id
     * @return
     */
    public PostViewModel getPost(int id) {
        PostViewModel pvm = new PostViewModel();
        pvm.setPostId(postClient.getPost(id).getPostId());
        pvm.setPostDate(postClient.getPost(id).getPostDate());
        pvm.setPosterName(postClient.getPost(id).getPosterName());
        pvm.setPost(postClient.getPost(id).getPost());
        pvm.setComments(commentClient.getComment(id));

        return pvm;
    }

    /**
     * Getting posts by poster name.
     * @param posterName
     * @return
     */
        public List<PostViewModel> getAllPostsByPoster(String posterName) {
            List<Post> pList = postClient.getPostsByPosterName(posterName);
            if(pList == null) {
                return null;
            }

            List<PostViewModel> pvmList = new ArrayList<>();

            for(Post post: pList){
                PostViewModel pvm = buildPostViewModel(post);
                pvmList.add(pvm);
            }
        return pvmList;
    }

    /**
     * Adding on the post and returning the postviewmodel.
     * @param pvm
     * @return
     */
    public PostViewModel addPost(PostViewModel pvm) {
        Post post = new Post();
        post.setPostDate(pvm.getPostDate());
        post.setPosterName(pvm.getPosterName());
        post.setPost(pvm.getPost());
        pvm.setPostId(postClient.createPost(post).getPostId());

        List<Comment>cList = pvm.getComments();

        for(Comment comment : cList) {
            if(comment.getCommentId() == 0) {
            comment.setCommentId(0);
        }
        System.out.println("SENDING MESSAGE");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, comment);
        System.out.println("TAKE THE MESSAGE");
    }
        return pvm;
    }

    // Helper Methods
    private PostViewModel buildPostViewModel(Post post) {
        PostViewModel pvm = new PostViewModel();
        pvm.setPostId(post.getPostId());
        pvm.setPostDate(post.getPostDate());
        pvm.setPosterName(post.getPosterName());
        pvm.setPost(post.getPost());
        pvm.setComments(commentClient.getComment(post.getPostId()));

        return pvm;
    }
}
