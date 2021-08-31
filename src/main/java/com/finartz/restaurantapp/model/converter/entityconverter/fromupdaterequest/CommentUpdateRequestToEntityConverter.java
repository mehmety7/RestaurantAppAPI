package com.finartz.restaurantapp.model.converter.entityconverter.fromupdaterequest;

import com.finartz.restaurantapp.exception.EntityNotFoundException;
import com.finartz.restaurantapp.model.entity.CommentEntity;
import com.finartz.restaurantapp.model.request.update.CommentUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CommentUpdateRequestToEntityConverter {

    public CommentEntity convert(final CommentUpdateRequest commentUpdateRequest, final CommentEntity commentExisting){
        if (Objects.isNull(commentUpdateRequest)){
            throw new EntityNotFoundException("Not found comment update request");
        }

        if (Objects.isNull(commentExisting)){
            throw new EntityNotFoundException("Not found existing comment record with given id");
        }

        if(Objects.nonNull(commentUpdateRequest.getComment())) {
            commentExisting.setComment(commentUpdateRequest.getComment());
        }

        if(Objects.nonNull(commentUpdateRequest.getTastePoint())) {
            commentExisting.setTastePoint(commentUpdateRequest.getTastePoint());
        }

        if(Objects.nonNull(commentUpdateRequest.getSpeedPoint())) {
            commentExisting.setSpeedPoint(commentUpdateRequest.getSpeedPoint());
        }

        return commentExisting;
    }


}
