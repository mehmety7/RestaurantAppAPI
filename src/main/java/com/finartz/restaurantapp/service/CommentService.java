package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Comment;

import java.util.List;

public interface CommentService {

    public Comment create(Comment comment);

    public List<Comment> getAll();

    public Comment getById(Long id);

    public Comment update(Comment comment);

    public Comment deleteById(Long id);
}
