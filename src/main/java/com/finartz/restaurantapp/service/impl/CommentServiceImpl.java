package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.converter.dto.CommentDtoConverter;
import com.finartz.restaurantapp.model.converter.entity.fromCreateRequest.CommentCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.converter.entity.fromUpdateRequest.CommentUpdateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.CommentDto;
import com.finartz.restaurantapp.model.entity.CommentEntity;
import com.finartz.restaurantapp.model.request.create.CommentCreateRequest;
import com.finartz.restaurantapp.model.request.update.CommentUpdateRequest;
import com.finartz.restaurantapp.repository.CommentRepository;
import com.finartz.restaurantapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentDtoConverter commentDtoConverter;
    private final CommentCreateRequestToEntityConverter commentCreateRequestToEntityConverter;
    private final CommentUpdateRequestToEntityConverter commentUpdateRequestToEntityConverter;


    @Override
    public CommentDto getComment(Long id){
        CommentEntity commentEntity = commentRepository.getById(id);
        return commentDtoConverter.convert(commentEntity);
    }

    @Override
    public CommentDto createComment(CommentCreateRequest commentCreateRequest){
        CommentEntity commentEntity = commentCreateRequestToEntityConverter.convert(commentCreateRequest);
        return commentDtoConverter.convert(commentRepository.save(commentEntity));
    }

    @Override
    public CommentDto updateComment(Long id, CommentUpdateRequest commentUpdateRequest){
        CommentEntity commentExisted = commentRepository.getById(id);

        CommentEntity commentUpdated =
                commentUpdateRequestToEntityConverter.convert(commentUpdateRequest, commentExisted);

        return commentDtoConverter.convert(commentRepository.save(commentUpdated));

    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
