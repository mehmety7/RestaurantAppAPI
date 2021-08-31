package com.finartz.restaurantapp.model.converter.entityconverter.fromupdaterequest;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.entity.CommentEntity;
import com.finartz.restaurantapp.model.request.update.CommentUpdateRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommentUpdateRequestToEntityConverterTest {

    @Spy
    private CommentUpdateRequestToEntityConverter commentUpdateRequestToEntityConverter;

    @Test
    public void whenPassValidCommentUpdateRequest_thenReturnCommentEntity(){
        CommentUpdateRequest commentUpdateRequest = CommentUpdateRequest.builder()
                .comment("Comment2")
                .speedPoint(5)
                .tastePoint(6)
                .build();

        CommentEntity commentExisting = CommentEntity.builder().comment("Comment1").build();
        String previousComment = commentExisting.getComment();

        CommentEntity commentUpdate = commentUpdateRequestToEntityConverter.convert(commentUpdateRequest, commentExisting);

        Assertions.assertEquals(commentUpdate.getComment(), commentUpdateRequest.getComment());
        Assertions.assertEquals(commentUpdate.getComment(), commentExisting.getComment());
        Assertions.assertNotEquals(commentUpdate.getComment(), previousComment);

    }

    @Test(expected = EntityNotFoundException.class)
    public void whenPassNullCommentUpdateRequest_thenThrowEntityNotFoundException(){
        commentUpdateRequestToEntityConverter.convert(null, CommentEntity.builder().build());
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenPassNullCommentExisting_thenThrowEntityNotFoundException(){
        commentUpdateRequestToEntityConverter.convert(CommentUpdateRequest.builder().build(), null);
    }

}
