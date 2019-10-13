package com.trilogyed.comment.controller;

import com.trilogyed.comment.DAO.CommentDao;
import com.trilogyed.comment.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/comments")
@CacheConfig(cacheNames = {"comments"})
public class CommentController {
    @Qualifier("commentDao")
    @Autowired
     CommentDao commentDao;

    /**
     * Creating the Comment object and storing to the Database
     * @param comment
     * @return
     */
    @CachePut(key = "#result.getCommentId()")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody Comment comment) {
        return commentDao.addComment(comment);
    }

    /**
     * Retrieving a Comment from the Database
     * @param id
     * @return
     */
    @Cacheable
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment getComment(@PathVariable("id") int id) {
        Comment returnComment = commentDao.getComment(id);
        if(returnComment == null) {
            throw new IllegalArgumentException("No Comment found with id : " + id);
        } else {
            return returnComment;
        }
    }

    /**
     * Gets the Comment from the database by the post id
     * @param post_id
     * @return
     */
    @GetMapping("post/{post_id}")
    public List<Comment> getCommentsByPostId(@PathVariable int post_id) {
        return commentDao.getCommentsByPostId(post_id);
    }

    /**
     * Retrieves all the comments from the database.
     * @return
     */
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> getAllComments() {
        return commentDao.getAllComments();
    }

    /**
     * Updates the comment based on the ID from the database
     * @param comment
     * @param id
     */
    @CacheEvict(key = "#comment.getCommentId()")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateComment(@RequestBody Comment comment, @PathVariable("id") int id) {
        comment.setCommentId(id);
        commentDao.updateComment(comment);
    }

    /**
     * Deletes the comment by the ID in the database.
     * @param id
     */
    @CacheEvict
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable("id") int id) {
        commentDao.deleteComment(id);
    }

}
