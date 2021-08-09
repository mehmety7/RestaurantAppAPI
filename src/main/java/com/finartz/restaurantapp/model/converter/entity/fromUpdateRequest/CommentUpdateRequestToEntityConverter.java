package com.finartz.restaurantapp.model.converter.entity.fromUpdateRequest;

import com.finartz.restaurantapp.model.entity.CommentEntity;
import com.finartz.restaurantapp.model.request.update.CommentUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentUpdateRequestToEntityConverter {

    public CommentEntity convert(final CommentUpdateRequest commentUpdateRequest,
                                 final CommentEntity commentExisted){
        if (commentUpdateRequest == null)
            return null;

        if(commentUpdateRequest.getComment() != null)
            commentExisted.setComment(commentUpdateRequest.getComment());

        if(commentUpdateRequest.getTastePoint() != null)
            commentExisted.setTastePoint(commentUpdateRequest.getTastePoint());

        if(commentUpdateRequest.getSpeedPoint() != null)
            commentExisted.setSpeedPoint(commentUpdateRequest.getSpeedPoint());

        return commentExisted;
    }


}
