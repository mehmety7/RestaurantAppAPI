package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.entity.CommentEntity;

import java.util.List;

public interface CommentService {

    public List<CommentEntity> getComments();

    public CommentEntity getComment(Long id);

    public CommentEntity createComment(CommentEntity commentEntity);

    public CommentEntity updateComment(CommentEntity commentEntity);

    public CommentEntity deleteComment(Long id);
}
