package com.trilogyed.comment.controller;

import com.trilogyed.comment.DAO.CommentDao;
import com.trilogyed.comment.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/comments")
public class CommentController {
    @Qualifier("commentDao")
    @Autowired
    CommentDao commentDao;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody Comment comment) {
        return commentDao.addComment(comment);
    }

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

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> getAllComments() {
        return commentDao.getAllComments();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateComment(@RequestBody Comment comment, @PathVariable("id") int id) {
        comment.setCommentId(id);
        commentDao.updateComment(comment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable("id") int id) {
        commentDao.deleteComment(id);
    }
}
