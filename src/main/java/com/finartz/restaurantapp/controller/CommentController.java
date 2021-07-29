package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.Comment;
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

    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody Comment comment){
        return new ResponseEntity(commentService.create(comment), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Comment> get(@PathVariable Long id){
        return new ResponseEntity(commentService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAll(){
        return new ResponseEntity(commentService.getAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Comment> update(@RequestBody Comment comment){
        return new ResponseEntity(commentService.update(comment), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Comment> deleteById(@PathVariable Long id){
        commentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
