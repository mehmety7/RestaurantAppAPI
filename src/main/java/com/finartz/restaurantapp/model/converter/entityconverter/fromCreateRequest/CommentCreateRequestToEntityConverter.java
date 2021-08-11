package com.finartz.restaurantapp.model.converter.entityconverter.fromCreateRequest;

import com.finartz.restaurantapp.model.converter.GenericConverter;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.dto.UserDto;
import com.finartz.restaurantapp.model.entity.BranchEntity;
import com.finartz.restaurantapp.model.entity.CommentEntity;
import com.finartz.restaurantapp.model.entity.UserEntity;
import com.finartz.restaurantapp.model.request.create.CommentCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentCreateRequestToEntityConverter implements GenericConverter<CommentCreateRequest, CommentEntity> {

    private final GenericConverter<UserDto, UserEntity> userEntityConverter;
    private final GenericConverter<BranchDto, BranchEntity> branchEntityConverter;

    @Override
    public CommentEntity convert(final CommentCreateRequest commentCreateRequest){
        if(commentCreateRequest == null){
            return null;
        }

        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setComment(commentCreateRequest.getComment());
        commentEntity.setSpeedPoint(commentCreateRequest.getSpeedPoint());
        commentEntity.setTastePoint(commentCreateRequest.getTastePoint());
        commentEntity.setUserEntity(convert(commentCreateRequest.getUser()));
        commentEntity.setBranchEntity(convert(commentCreateRequest.getBranch()));

        return commentEntity;
    }

    private UserEntity convert(final UserDto user){
        return userEntityConverter.convert(user);
    }

    private BranchEntity convert(final BranchDto branch){
        return branchEntityConverter.convert(branch);
    }

}