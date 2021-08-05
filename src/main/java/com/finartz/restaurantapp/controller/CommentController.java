package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.entity.CommentEntity;
import com.finartz.restaurantapp.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<CommentEntity> getComment(@PathVariable Long id){
        return new ResponseEntity(commentService.getComment(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CommentEntity>> getComments(){
        return new ResponseEntity(commentService.getComments(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentEntity> createComment(@RequestBody CommentEntity commentEntity){
        return new ResponseEntity(commentService.createComment(commentEntity), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CommentEntity> updateComment(@RequestBody CommentEntity commentEntity){
        return new ResponseEntity(commentService.updateComment(commentEntity), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CommentEntity> deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
