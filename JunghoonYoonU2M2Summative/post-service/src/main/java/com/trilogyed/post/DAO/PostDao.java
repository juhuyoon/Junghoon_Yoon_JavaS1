package com.trilogyed.post.DAO;

import com.trilogyed.post.model.Post;

import java.util.List;

public interface PostDao {
     Post addPost(Post post);
     List<Post> getAllPosts();
     List<Post> getPostsByPosterName(String posterName);
     Post getPost(int postId);
     void updatePost(Post post);
     void deletePost(int postId);


}
