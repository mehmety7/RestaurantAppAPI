package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.dtoconverter.CommentDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromcreaterequest.CommentCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromupdaterequest.CommentUpdateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.CommentDto;
import com.finartz.restaurantapp.model.entity.CommentEntity;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.request.create.CommentCreateRequest;
import com.finartz.restaurantapp.model.request.update.CommentUpdateRequest;
import com.finartz.restaurantapp.repository.CommentRepository;
import com.finartz.restaurantapp.service.impl.CommentServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

    private static final String COMMENT_HARIKA = "Harika";
    private static final String COMMENT_ORTALAMA = "Ortalama";

    @InjectMocks
    private CommentServiceImpl commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentDtoConverter commentDtoConverter;

    @Mock
    private CommentCreateRequestToEntityConverter commentCreateRequestToEntityConverter;

    @Mock
    private CommentUpdateRequestToEntityConverter commentUpdateRequestToEntityConverter;

    @Mock
    private TokenService tokenService;

    @Test
    public void whenFetchByValidId_thenReturnComment() {
        CommentEntity commentEntity = CommentEntity.builder().comment(COMMENT_HARIKA).build();
        CommentDto comment = CommentDto.builder().comment(COMMENT_HARIKA).build();

        Mockito.when(commentDtoConverter.convert(commentEntity)).thenReturn(comment);
        Mockito.when(commentRepository.findById(1L)).thenReturn(Optional.ofNullable(commentEntity));

        CommentDto resultComment = commentService.getComment(1L);

        assertEquals(comment, resultComment);
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenFetchByInvalidId_thenThrowEntityNotFoundException() {

        Mockito.when(commentRepository.findById(anyLong())).thenReturn(Optional.empty());
        commentService.getComment(anyLong());

    }

    @Test
    public void whenAddComment_thenReturnSavedComment() {
        UserEntity userEntity = UserEntity.builder().id(1l).build();
        CommentEntity commentEntity = CommentEntity.builder().userEntity(userEntity).comment(COMMENT_HARIKA).build();
        CommentDto comment = CommentDto.builder().userId(1l).comment(COMMENT_HARIKA).build();
        CommentCreateRequest commentCreateRequest = CommentCreateRequest.builder().userId(1l).build();

        Mockito.when(tokenService.isRequestOwnerAuthoritative(commentCreateRequest.getUserId())).thenReturn(true);
        Mockito.when(commentDtoConverter.convert(commentEntity)).thenReturn(comment);
        Mockito.when(commentCreateRequestToEntityConverter.convert(commentCreateRequest)).thenReturn(commentEntity);
        Mockito.when(commentRepository.save(commentEntity)).thenReturn(commentEntity);

        CommentDto resultComment = commentService.createComment(commentCreateRequest);

        assertEquals(comment.getComment(), resultComment.getComment());
    }

    @Test
    public void whenUpdateComment_thenReturnUpdatedComment(){
        UserEntity userEntity = UserEntity.builder().id(1l).build();
        CommentEntity commentEntity = CommentEntity.builder().id(1l).userEntity(userEntity).comment(COMMENT_HARIKA).build();
        CommentEntity commentEntityUpdated = CommentEntity.builder().id(1l).userEntity(userEntity).comment(COMMENT_ORTALAMA).build();
        CommentDto comment = CommentDto.builder().id(1l).comment(COMMENT_HARIKA).build();
        CommentDto commentUpdated = CommentDto.builder().id(1l).comment(COMMENT_ORTALAMA).build();
        CommentUpdateRequest commentUpdateRequest = CommentUpdateRequest.builder().build();

        Mockito.when(tokenService.isRequestOwnerAuthoritative(commentEntity.getUserEntity().getId())).thenReturn(true);
        Mockito.when(commentRepository.findById(1l)).thenReturn(Optional.of(commentEntity));
        Mockito.when(commentUpdateRequestToEntityConverter.convert(commentUpdateRequest, commentEntity)).thenReturn(commentEntityUpdated);
        Mockito.when(commentRepository.save(commentEntityUpdated)).thenReturn(commentEntityUpdated);
        Mockito.when(commentDtoConverter.convert(commentEntityUpdated)).thenReturn(commentUpdated);

        CommentDto resultComment = commentService.updateComment(1L, commentUpdateRequest);

        Assertions.assertNotEquals(comment.getComment(), COMMENT_ORTALAMA);
        Assertions.assertEquals(resultComment.getComment(), COMMENT_ORTALAMA);

    }

    @Test
    public void whenDeleteComment_thenReturnNothing(){
        commentService.deleteComment(anyLong());
        Mockito.verify(commentRepository).deleteById(anyLong());
    }

}
