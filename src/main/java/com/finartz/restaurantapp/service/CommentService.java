package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Comment;
import com.finartz.restaurantapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment create(Comment comment){
        Comment save = commentRepository.save(comment);
        return save;
    }

    public List<Comment> getAll(){
        List<Comment> comments = commentRepository.findAll();
        return comments;
    }

    public Comment getById(Long id){
        Comment comment = commentRepository.getById(id);
        return comment;
    }

    public Comment update(Comment comment){
        Comment update = commentRepository.getById(comment.getId());
        if(update != null) {
            commentRepository.save(comment);
            return update;
        }
        return comment;
    }

    public Comment deleteById(Long id){
        Comment comment = commentRepository.getById(id);
        if (comment != null) {
            commentRepository.deleteById(id);
            return comment;
        }
        return comment;
    }
}
