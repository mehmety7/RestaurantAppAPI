package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.entity.CommentEntity;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.request.create.CommentCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CommentCreateRequestToEntityConverter implements GenericConverter<CommentCreateRequest, CommentEntity> {

    @Override
    public CommentEntity convert(final CommentCreateRequest commentCreateRequest){
        if (Objects.isNull(commentCreateRequest)){
            throw new EntityNotFoundException("Not found comment create request");
        }

        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setComment(commentCreateRequest.getComment());
        commentEntity.setSpeedPoint(commentCreateRequest.getSpeedPoint());
        commentEntity.setTastePoint(commentCreateRequest.getTastePoint());

        UserEntity userEntity = new UserEntity();
        if (Objects.nonNull(commentCreateRequest.getUserId())){
            userEntity.setId(commentCreateRequest.getUserId());
        }
        commentEntity.setUserEntity(userEntity);

        BranchEntity branchEntity = new BranchEntity();
        if (Objects.nonNull(commentCreateRequest.getBranchId())){
            branchEntity.setId(commentCreateRequest.getBranchId());
        }
        commentEntity.setBranchEntity(branchEntity);

        return commentEntity;
    }

}