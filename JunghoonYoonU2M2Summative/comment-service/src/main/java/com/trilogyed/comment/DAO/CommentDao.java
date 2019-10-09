package com.trilogyed.comment.DAO;

import com.trilogyed.comment.model.Comment;

import java.util.List;

public interface CommentDao {
    Comment addComment(Comment comment);
    List<Comment> getAllComments();
    Comment getComment(int commentId);
    void updateComment(Comment comment);
    void deleteComment(int commentId);

}
