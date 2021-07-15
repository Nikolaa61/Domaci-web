package com.example.domaci7.repositories.post;

import com.example.domaci7.entities.Post;

import java.util.List;

public interface PostRepository {
   public Post addPost(Post post);
   public List<Post> allPosts();
   public Post findPost(Integer id);
}
