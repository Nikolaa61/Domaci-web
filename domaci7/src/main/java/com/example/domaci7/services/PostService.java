package com.example.domaci7.services;

import com.example.domaci7.entities.Post;
import com.example.domaci7.repositories.post.PostRepository;

import javax.inject.Inject;
import java.util.List;

public class PostService {
    @Inject
    private PostRepository postRepository;

    public Post addPost(Post post) {
        return this.postRepository.addPost(post);
    }

    public List<Post> allPosts() { return this.postRepository.allPosts();}

    public Post findPost(Integer id) { return this.postRepository.findPost(id);}

}
