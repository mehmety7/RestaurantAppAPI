package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.converter.dtoconverter.CommentDtoConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest.CommentCreateRequestToEntityConverter;
import com.finartz.restaurantapp.model.converter.entityconverter.fromUpdateRequest.CommentUpdateRequestToEntityConverter;
import com.finartz.restaurantapp.model.dto.CommentDto;
import com.finartz.restaurantapp.model.entity.CommentEntity;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    public void whenFetchById_thenReturnComment() {
        CommentEntity commentEntity = CommentEntity.builder().comment(COMMENT_HARIKA).build();
        CommentDto comment = CommentDto.builder().comment(COMMENT_HARIKA).build();

        Mockito.when(commentDtoConverter.convert(commentEntity)).thenReturn(comment);
        Mockito.when(commentRepository.getById(1L)).thenReturn(commentEntity);

        CommentDto resultComment = commentService.getComment(1L);

        assertEquals(comment, resultComment);
    }

    @Test
    public void whenAddComment_thenReturnSavedComment() {
        CommentEntity commentEntity = CommentEntity.builder().comment(COMMENT_HARIKA).build();
        CommentDto comment = CommentDto.builder().comment(COMMENT_HARIKA).build();
        CommentCreateRequest commentCreateRequest = CommentCreateRequest.builder().build();

        Mockito.when(commentDtoConverter.convert(commentEntity)).thenReturn(comment);
        Mockito.when(commentCreateRequestToEntityConverter.convert(commentCreateRequest)).thenReturn(commentEntity);
        Mockito.when(commentRepository.save(commentEntity)).thenReturn(commentEntity);

        CommentDto resultComment = commentService.createComment(commentCreateRequest);

        assertEquals(comment.getComment(), resultComment.getComment());
    }

    @Test
    public void whenUpdateComment_thenReturnUpdatedComment(){
        CommentEntity commentEntity = CommentEntity.builder().id(1l).comment(COMMENT_HARIKA).build();
        CommentEntity commentEntityUpdated = CommentEntity.builder().id(1l).comment(COMMENT_ORTALAMA).build();
        CommentDto comment = CommentDto.builder().id(1l).comment(COMMENT_HARIKA).build();
        CommentDto commentUpdated = CommentDto.builder().id(1l).comment(COMMENT_ORTALAMA).build();
        CommentUpdateRequest commentUpdateRequest = CommentUpdateRequest.builder().build();

        Mockito.when(commentRepository.getById(1l)).thenReturn(commentEntity);
        Mockito.when(commentUpdateRequestToEntityConverter.convert(commentUpdateRequest, commentEntity)).thenReturn(commentEntityUpdated);
        Mockito.when(commentRepository.save(commentEntityUpdated)).thenReturn(commentEntityUpdated);
        Mockito.when(commentDtoConverter.convert(commentEntityUpdated)).thenReturn(commentUpdated);

        CommentDto resultComment = commentService.updateComment(1L, commentUpdateRequest);

        Assertions.assertNotEquals(comment.getComment(), COMMENT_ORTALAMA);
        Assertions.assertEquals(resultComment.getComment(), COMMENT_ORTALAMA);

    }

    @Test
    public void whenDeleteComment_thenReturnNothing(){
        commentService.deleteComment(Mockito.anyLong());
        Mockito.verify(commentRepository).deleteById(Mockito.anyLong());
    }

}