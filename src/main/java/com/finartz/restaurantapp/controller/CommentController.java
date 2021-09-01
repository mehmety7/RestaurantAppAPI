package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.CommentDto;
import com.finartz.restaurantapp.model.request.create.CommentCreateRequest;
import com.finartz.restaurantapp.model.request.update.CommentUpdateRequest;
import com.finartz.restaurantapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Long id){
        return new ResponseEntity(commentService.getComment(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentCreateRequest commentCreateRequest){
        return new ResponseEntity(commentService.createComment(commentCreateRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CommentDto> updateComment(@Valid @RequestBody CommentUpdateRequest commentUpdateRequest){
        return new ResponseEntity(commentService.updateComment(commentUpdateRequest), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
