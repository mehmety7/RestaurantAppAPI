package com.finartz.restaurantapp.model.converter.dtoconverter;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.dto.CommentDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.entity.CommentEntity;
import com.finartz.restaurantapp.model.entity.UserEntity;
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
                .userEntity(UserEntity.builder().id(1l).build())
                .branchEntity(BranchEntity.builder().id(1l).build())
                .build();

        CommentDto commentDto = commentDtoConverter.convert(commentEntity);

        Assertions.assertEquals(commentDto.getComment(), commentEntity.getComment());

    }

    @Test(expected = EntityNotFoundException.class)
    public void whenPassNullCommentEntity_thenReturnThrowEntityNotFoundException(){
        commentDtoConverter.convert(null);
    }

}
