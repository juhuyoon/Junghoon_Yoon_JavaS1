package com.trilogyed.comment.DAO;

import com.trilogyed.comment.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("commentDao")
public class CommentDaoTempImpl implements CommentDao {

    private JdbcTemplate jdbcTemplate;
    //Prepared Statement
    private static final String ADD_COMMENT_SQL =
            "INSERT INTO comment(post_id, create_date, commenter_name, comment) values(?, ?, ?, ?)";

    private static final String SELECT_COMMENT_SQL =
            "SELECT * FROM comment WHERE comment_id = ?";

    private static final String SELECT_ALL_COMMENTS_SQL =
            "SELECT * FROM comment";

    private static final String UPDATE_COMMENT_SQL =
            "UPDATE comment SET post_id = ?, create_date = ?, commenter_name = ?, comment = ? where comment_id = ?";

    private static final String DELETE_COMMENT_SQL =
            "DELETE FROM comment where comment_id = ?";

    @Autowired
    public CommentDaoTempImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Adds comment to DB
     * @param comment
     * @return
     */
    @Override
    public Comment addComment(Comment comment) {
        jdbcTemplate.update(ADD_COMMENT_SQL,
                comment.getPostId(),
                comment.getCreateDate(),
                comment.getCommenterName(),
                comment.getComment());
        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        comment.setCommentId(id);
        return comment;
    }

    /**
     * Calls all comments from the database
     * @return
     */
    @Override
    public List<Comment> getAllComments() {
        return jdbcTemplate.query(SELECT_ALL_COMMENTS_SQL, this::mapRowToComment);
    }

    /**
     * Gets a comment from a database by ID
     * @param commentId
     * @return
     */
    @Override
    public Comment getComment(int commentId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_COMMENT_SQL, this::mapRowToComment, commentId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Updates a comment from the database by ID
     * @param comment
     */
    @Override
    public void updateComment(Comment comment) {
        jdbcTemplate.update(UPDATE_COMMENT_SQL,
                comment.getPostId(),
                comment.getCreateDate(),
                comment.getCommenterName(),
                comment.getComment(),
                comment.getCommentId());

    }

    /**
     * Deletes a comment from the Database by ID.
     * @param commentId
     */
    @Override
    public void deleteComment(int commentId) {
        jdbcTemplate.update(DELETE_COMMENT_SQL, commentId);
    }

    private Comment mapRowToComment(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();
        comment.setCommentId(rs.getInt("comment_id"));
        comment.setPostId(rs.getInt("post_id"));
        comment.setCreateDate(rs.getDate("create_date").toLocalDate());
        comment.setCommenterName(rs.getString("commenter_name"));
        comment.setComment(rs.getString("comment"));

        return comment;
    }

}
