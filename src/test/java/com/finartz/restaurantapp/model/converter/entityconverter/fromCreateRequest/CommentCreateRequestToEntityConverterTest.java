package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

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
                .build();

        CommentEntity commentEntity = commentCreateRequestToEntityConverter.convert(commentCreateRequest);

        Assertions.assertEquals(commentEntity.getComment(), commentCreateRequest.getComment());
    }

}