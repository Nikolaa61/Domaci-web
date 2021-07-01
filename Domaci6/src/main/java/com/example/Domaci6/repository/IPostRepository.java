package com.example.Domaci6.repository;

import com.example.Domaci6.models.Post;

import java.util.List;

public interface IPostRepository {
    public List<Post> all();

    public void insert(Post post);

    public Post find(int id);
}
