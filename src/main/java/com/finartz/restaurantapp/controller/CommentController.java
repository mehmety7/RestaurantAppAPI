package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.Branch;
import com.finartz.restaurantapp.model.Comment;
import com.finartz.restaurantapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("create")
    public ResponseEntity<Comment> create(@RequestBody Comment comment, @RequestParam User user, @RequestParam Branch branch){
        return new ResponseEntity<>(commentService.create(comment, user, branch), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<Comment> update(@RequestBody Comment comment){
        return new ResponseEntity<>(commentService.update(comment), HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Comment> get(@PathVariable Long id){
        return new ResponseEntity<>(commentService.getById(id), HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Comment>> getAll(){
        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK)
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        commentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
