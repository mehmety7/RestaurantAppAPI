package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.entity.CommentEntity;
import com.finartz.restaurantapp.repository.CommentRepository;
import com.finartz.restaurantapp.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentEntity> getComments(){
        List<CommentEntity> commentEntities = commentRepository.findAll();
        return commentEntities;
    }

    @Override
    public CommentEntity getComment(Long id){
        CommentEntity commentEntity = commentRepository.getById(id);
        return commentEntity;
    }

    @Override
    public CommentEntity createComment(CommentEntity commentEntity){
        CommentEntity save = commentRepository.save(commentEntity);
        return save;
    }

    @Override
    public CommentEntity updateComment(CommentEntity commentEntity){
        CommentEntity foundCommentEntity = commentRepository.getById(commentEntity.getId());
        if(commentEntity.getComment() != null)
            foundCommentEntity.setComment(commentEntity.getComment());
        if(commentEntity.getSpeedPoint() != null)
            foundCommentEntity.setSpeedPoint(commentEntity.getSpeedPoint());
        if(commentEntity.getTastePoint() != null)
            foundCommentEntity.setTastePoint(commentEntity.getTastePoint());
        if(commentEntity.getUserEntity() != null)
            foundCommentEntity.setUserEntity(commentEntity.getUserEntity());
        if(commentEntity.getBranchEntity() != null)
            foundCommentEntity.setBranchEntity(commentEntity.getBranchEntity());

        return commentRepository.save(foundCommentEntity);

    }

    @Override
    public CommentEntity deleteComment(Long id){
        CommentEntity commentEntity = commentRepository.getById(id);
        if (commentEntity != null) {
            commentRepository.deleteById(id);
            return commentEntity;
        }
        return commentEntity;
    }
}
