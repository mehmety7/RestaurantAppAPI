package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.dto.CommentDto;
import com.finartz.restaurantapp.model.request.create.CommentCreateRequest;
import com.finartz.restaurantapp.model.request.update.CommentUpdateRequest;

public interface CommentService {

    CommentDto getComment(Long id);

    CommentDto createComment(CommentCreateRequest commentCreateRequest);

    CommentDto updateComment(CommentUpdateRequest commentUpdateRequest);

    void deleteComment(Long id);
}
