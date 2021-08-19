package com.finartz.restaurantapp.model.converter.entityconverter.fromUpdateRequest;

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
                .build();

        CommentEntity commentExisting = CommentEntity.builder().comment("Comment1").build();
        String previousComment = commentExisting.getComment();

        CommentEntity commentUpdate = commentUpdateRequestToEntityConverter.convert(commentUpdateRequest, commentExisting);

        Assertions.assertEquals(commentUpdate.getComment(), commentUpdateRequest.getComment());
        Assertions.assertEquals(commentUpdate.getComment(), commentExisting.getComment());
        Assertions.assertNotEquals(commentUpdate.getComment(), previousComment);

    }

}
