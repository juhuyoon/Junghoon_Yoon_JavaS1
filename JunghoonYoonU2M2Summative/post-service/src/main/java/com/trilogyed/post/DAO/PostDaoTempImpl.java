package com.trilogyed.post.DAO;

import com.trilogyed.post.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("postDao")
public class PostDaoTempImpl implements PostDao {
    private JdbcTemplate jdbcTemplate;

    //Prepared Statements
    private static final String ADD_POST_SQL =
            "INSERT INTO post(post_date, poster_name, post) values(?, ?, ?)";

    private static final String SELECT_POST_SQL =
            "SELECT * FROM post WHERE post_id = ?";

    private static final String SELECT_POST_BY_NAME_SQL =
            "SELECT * FROM post WHERE poster_name = ?";

    private static final String SELECT_ALL_POSTS_SQL =
            "SELECT * FROM post";

    private static final String UPDATE_POST_SQL =
            "UPDATE post SET post_date = ?, post_name = ?, post = ?, where post_id = ?";

    private static final String DELETE_POST_SQL =
            "DELETE FROM POST where post_id = ?";

    @Autowired

    public PostDaoTempImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Adds a post to the database
     * @param post
     * @return
     */
    @Override
    @Transactional
    public Post addPost(Post post) {
        jdbcTemplate.update(
                ADD_POST_SQL,
                post.getPostDate(),
                post.getPosterName(),
                post.getPost()
        );

        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        post.setPostID(id);

        return post;
    }

    /**
     * Calls all the posts from the database
     * @return
     */
    @Override
    public List<Post> getAllPosts() {
        return jdbcTemplate.query(SELECT_ALL_POSTS_SQL, this::mapRowToPost);
    }

    /**
     * Gets a post from the database based on the ID
     * @param postId
     * @return
     */
    @Override
    public Post getPost(int postId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_POST_SQL, this::mapRowToPost, postId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Getting All Posts fromt he database based on the poster name.
     * @param posterName
     * @return
     */
    public List<Post> getPostsByPosterName(String posterName){
        return jdbcTemplate.query(SELECT_POST_BY_NAME_SQL, this::mapRowToPost, posterName);
    }

    /**
     * Updates a post from the database based on the ID
     * @param post
     */
    @Override
    public void updatePost(Post post) {
        jdbcTemplate.update(UPDATE_POST_SQL,
                post.getPostDate(),
                post.getPosterName(),
                post.getPost(),
                post.getPostID());
    }

    /**
     * Deletes a post from the database based on the ID
     * @param postId
     */
    @Override
    public void deletePost(int postId) {
        jdbcTemplate.update(DELETE_POST_SQL, postId);
    }

    private Post mapRowToPost(ResultSet rs, int rowNum) throws SQLException {
        Post post = new Post();
        post.setPostID(rs.getInt("post_id"));
        post.setPostDate(rs.getDate("post_date").toLocalDate());
        post.setPosterName(rs.getString("poster_name"));
        post.setPost(rs.getString("post"));

        return post;
    }
}
