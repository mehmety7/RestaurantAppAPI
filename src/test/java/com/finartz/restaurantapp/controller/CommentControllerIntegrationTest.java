package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.CommentDto;
import com.finartz.restaurantapp.model.request.create.CommentCreateRequest;
import com.finartz.restaurantapp.model.request.update.CommentUpdateRequest;
import com.finartz.restaurantapp.service.TokenService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentControllerIntegrationTest {

    @Autowired
    private CommentController commentController;

    @MockBean
    private TokenService tokenService;

    @BeforeEach
    public void init(){
        Mockito.doNothing().when(tokenService).checkRequestOwnerAuthoritative(anyLong());
    }

    @Test
    public void whenGetCommentById_thenReturnComment() {
        ResponseEntity<CommentDto> response = commentController.getComment(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1L, Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    public void whenCreateNewComment_thenReturnComment() {
        CommentCreateRequest commentCreateRequest = CommentCreateRequest
                .builder()
                .comment("").tastePoint(8).speedPoint(9)
                .userId(3L).branchId(1L)
                .build();

        ResponseEntity<CommentDto> response = commentController.createComment(commentCreateRequest);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void whenUpdateComment_thenReturnComment() {
        CommentUpdateRequest commentUpdateRequest = CommentUpdateRequest
                .builder()
                .id(1L)
                .speedPoint(5)
                .build();

        ResponseEntity<CommentDto> response = commentController.updateComment(commentUpdateRequest);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void whenZDeleteComment_thenReturnComment(){
        ResponseEntity<Void> response = commentController.deleteComment(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }




}
