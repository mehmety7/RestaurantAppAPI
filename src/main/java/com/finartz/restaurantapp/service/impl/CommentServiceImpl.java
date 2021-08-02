package com.finartz.restaurantapp.service.impl;

import com.finartz.restaurantapp.model.Comment;
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
    public Comment create(Comment comment){
        Comment save = commentRepository.save(comment);
        return save;
    }

    @Override
    public List<Comment> getAll(){
        List<Comment> comments = commentRepository.findAll();
        return comments;
    }

    @Override
    public Comment getById(Long id){
        Comment comment = commentRepository.getById(id);
        return comment;
    }

    @Override
    public Comment update(Comment comment){
        Comment foundComment = commentRepository.getById(comment.getId());
        if(comment.getComment() != null)
            foundComment.setComment(comment.getComment());
        if(comment.getSpeedPoint() != null)
            foundComment.setSpeedPoint(comment.getSpeedPoint());
        if(comment.getTastePoint() != null)
            foundComment.setTastePoint(comment.getTastePoint());
        if(comment.getUser() != null)
            foundComment.setUser(comment.getUser());
        if(comment.getBranch() != null)
            foundComment.setBranch(comment.getBranch());

        return commentRepository.save(foundComment);

    }

    @Override
    public Comment deleteById(Long id){
        Comment comment = commentRepository.getById(id);
        if (comment != null) {
            commentRepository.deleteById(id);
            return comment;
        }
        return comment;
    }
}
