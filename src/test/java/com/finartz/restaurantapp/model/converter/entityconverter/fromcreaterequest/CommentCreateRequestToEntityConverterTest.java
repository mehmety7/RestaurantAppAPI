package com.finartz.restaurantapp.model.converter.entityconverter.fromcreaterequest;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.entity.CommentEntity;
import com.finartz.restaurantapp.model.request.create.CommentCreateRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommentCreateRequestToEntityConverterTest {

    @Spy
    private CommentCreateRequestToEntityConverter commentCreateRequestToEntityConverter;

    @Test
    public void whenPassValidCommentCreateRequest_thenReturnCommentEntity(){
        CommentCreateRequest commentCreateRequest = CommentCreateRequest.builder()
                .comment("Comment")
                .userId(1L)
                .branchId(1L)
                .build();

        CommentEntity commentEntity = commentCreateRequestToEntityConverter.convert(commentCreateRequest);

        Assertions.assertEquals(commentEntity.getComment(), commentCreateRequest.getComment());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenPassNullCommentCreateRequest_thenThrowEntityNotFoundException(){
        commentCreateRequestToEntityConverter.convert(null);
    }

}