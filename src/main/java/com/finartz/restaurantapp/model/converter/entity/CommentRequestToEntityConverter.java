package com.finartz.restaurantapp.model.converter.entity;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.entity.CommentEntity;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.request.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentRequestToEntityConverter implements GenericConverter<CommentRequest, CommentEntity> {

    private final GenericConverter<UserDto, UserEntity> userEntityConverter;
    private final GenericConverter<BranchDto, BranchEntity> branchEntityConverter;

    @Override
    public CommentEntity convert(final CommentRequest commentRequest){
        if(commentRequest == null){
            return null;
        }

        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setId(commentRequest.getId());
        commentEntity.setComment(commentRequest.getComment());
        commentEntity.setSpeedPoint(commentRequest.getSpeedPoint());
        commentEntity.setTastePoint(commentRequest.getTastePoint());
        commentEntity.setUserEntity(convert(commentRequest.getUser()));
        commentEntity.setBranchEntity(convert(commentRequest.getBranch()));

        return commentEntity;
    }

    private UserEntity convert(final UserDto user){
        return userEntityConverter.convert(user);
    }

    private BranchEntity convert(final BranchDto branch){
        return branchEntityConverter.convert(branch);
    }

}