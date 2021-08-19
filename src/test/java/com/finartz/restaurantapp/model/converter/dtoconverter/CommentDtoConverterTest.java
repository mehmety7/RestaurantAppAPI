package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.model.dto.CommentDto;
import com.finartz.restaurantapp.model.entity.CommentEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommentDtoConverterTest {

    @Spy
    private CommentDtoConverter commentDtoConverter;

    @Test
    public void whenPassValidCommentEntity_thenReturnCommentDto(){
        CommentEntity commentEntity = CommentEntity.builder()
                .id(1l)
                .comment("Comment")
                .build();

        CommentDto commentDto = commentDtoConverter.convert(commentEntity);

        Assertions.assertEquals(commentDto.getComment(), commentEntity.getComment());

    }

}
