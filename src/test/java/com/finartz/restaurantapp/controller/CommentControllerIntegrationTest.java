package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.dto.CommentDto;
import com.finartz.restaurantapp.model.request.create.CommentCreateRequest;
import com.finartz.restaurantapp.model.request.update.CommentUpdateRequest;
import com.finartz.restaurantapp.service.TokenService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@TestPropertySource("classpath:application-test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentControllerIntegrationTest {

    @Autowired
    private CommentController commentController;

    @MockBean
    private TokenService tokenService;

    @BeforeEach
    public void init(){
        Mockito.when(tokenService.isRequestOwnerAuthoritative(anyLong())).thenReturn(true);
    }

    @Test
    public void whenGetCommentById_thenReturnComment() {
        ResponseEntity<CommentDto> response = commentController.getComment(1l);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getId(), 1l);
    }

    @Test
    public void whenCreateNewComment_thenReturnComment() {
        CommentCreateRequest commentCreateRequest = CommentCreateRequest
                .builder()
                .comment("").tastePoint(8).speedPoint(9)
                .userId(3l).branchId(1l)
                .build();

        ResponseEntity<CommentDto> response = commentController.createComment(commentCreateRequest);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void whenUpdateComment_thenReturnComment() {
        CommentUpdateRequest commentUpdateRequest = CommentUpdateRequest
                .builder()
                .speedPoint(5)
                .build();

        ResponseEntity<CommentDto> response = commentController.updateComment(1l, commentUpdateRequest);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void whenZDeleteComment_thenReturnComment(){
        ResponseEntity response = commentController.deleteComment(1L);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }




}
