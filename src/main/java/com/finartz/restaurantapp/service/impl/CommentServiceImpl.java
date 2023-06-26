package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.dtoconverter.CommentDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromcreaterequest.CommentCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromupdaterequest.CommentUpdateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.CommentDto;
import com.finartz.restaurantapp.model.entity.CommentEntity;
import com.finartz.restaurantapp.model.request.create.CommentCreateRequest;
import com.finartz.restaurantapp.model.request.update.CommentUpdateRequest;
import com.finartz.restaurantapp.repository.CommentRepository;
import com.finartz.restaurantapp.service.CommentService;
import com.finartz.restaurantapp.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentDtoConverter commentDtoConverter;
    private final CommentCreateRequestToEntityConverter commentCreateRequestToEntityConverter;
    private final CommentUpdateRequestToEntityConverter commentUpdateRequestToEntityConverter;

    private final TokenService tokenService;

    @Override
    public CommentDto getComment(Long id){
        return commentDtoConverter.convert(commentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found Comment with id: " + id)
        ));
    }

    @Override
    public CommentDto createComment(CommentCreateRequest commentCreateRequest){
        tokenService.checkRequestOwnerAuthoritative(commentCreateRequest.getUserId());
        CommentEntity commentEntity = commentCreateRequestToEntityConverter.convert(commentCreateRequest);
        return commentDtoConverter.convert(commentRepository.save(commentEntity));
    }

    @Override
    @Transactional
    public CommentDto updateComment(CommentUpdateRequest commentUpdateRequest){
        CommentEntity commentExisted = commentRepository.findById(commentUpdateRequest.getId()).orElseThrow(
                () -> new EntityNotFoundException("Not found comment entity with id: " + commentUpdateRequest.getId())
        );
        tokenService.checkRequestOwnerAuthoritative(commentExisted.getUserEntity().getId());
        CommentEntity commentUpdated = commentUpdateRequestToEntityConverter.convert(commentUpdateRequest, commentExisted);
        return commentDtoConverter.convert(commentRepository.save(commentUpdated));
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
