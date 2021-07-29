package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Comment;
import com.finartz.restaurantapp.repository.CommentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentRepository commentRepository;


    @Test
    public void whenFetchAll_thenReturnAllComment() {
        Comment comment1 = Comment.builder().id(1l).comment("Harika").build();
        Comment comment2 = Comment.builder().id(2l).comment("Ortalama").build();
        List<Comment> commentList = Arrays.asList(comment1, comment2);

        Mockito.when(commentRepository.findAll()).thenReturn(commentList);

        List<Comment> fetchedList = commentService.getAll();

        assertEquals(commentList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnComment() {
        Comment comment = Comment.builder().comment("Harika").build();

        Mockito.when(commentRepository.getById(1L)).thenReturn(comment);

        Comment fetchedComment = commentService.getById(1L);

        assertEquals(comment.getId(), fetchedComment.getId());
    }

    @Test
    public void whenAddComment_thenReturnSavedComment() {
        Comment comment = Comment.builder().comment("Harika").build();

        Mockito.doReturn(comment).when(commentRepository).save(comment);

        Comment returnedComment = commentService.create(comment);

        assertEquals(comment.getComment(), returnedComment.getComment());
    }

    @Test
    public void whenUpdateComment_thenReturnUpdatedComment(){
        Comment comment = Comment.builder().comment("Harika").build();

        Mockito.when(commentRepository.save(comment)).thenReturn(comment);

        Comment updatedComment = commentService.update(comment);

        assertEquals(comment , updatedComment);

    }

}
