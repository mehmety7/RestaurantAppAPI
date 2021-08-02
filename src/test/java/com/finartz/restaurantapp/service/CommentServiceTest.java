package com.finartz.restaurantapp.service;

import com.finartz.restaurantapp.model.Comment;
import com.finartz.restaurantapp.repository.CommentRepository;
import com.finartz.restaurantapp.service.impl.CommentServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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

    private static final String COMMENT_HARIKA = "Harika";
    private static final String COMMENT_ORTALAMA = "Ortalama";

    @InjectMocks
    private CommentServiceImpl commentService;

    @Mock
    private CommentRepository commentRepository;


    @Test
    public void whenFetchAll_thenReturnAllComment() {
        Comment comment1 = Comment.builder().id(1l).comment(COMMENT_HARIKA).build();
        Comment comment2 = Comment.builder().id(2l).comment(COMMENT_ORTALAMA).build();
        List<Comment> commentList = Arrays.asList(comment1, comment2);

        Mockito.when(commentRepository.findAll()).thenReturn(commentList);

        List<Comment> fetchedList = commentService.getAll();

        assertEquals(commentList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnComment() {
        Comment comment = Comment.builder().comment(COMMENT_HARIKA).build();

        Mockito.when(commentRepository.getById(1L)).thenReturn(comment);

        Comment fetchedComment = commentService.getById(1L);

        assertEquals(comment.getId(), fetchedComment.getId());
    }

    @Test
    public void whenAddComment_thenReturnSavedComment() {
        Comment comment = Comment.builder().comment(COMMENT_HARIKA).build();

        Mockito.doReturn(comment).when(commentRepository).save(comment);

        Comment returnedComment = commentService.create(comment);

        assertEquals(comment.getComment(), returnedComment.getComment());
    }

    @Test
    public void whenUpdateComment_thenReturnUpdatedComment(){
        Comment foundComment = Comment.builder().id(1l).comment(COMMENT_HARIKA).build();
        Comment modifyComment = Comment.builder().id(1l).comment(COMMENT_ORTALAMA).build();

        Mockito.when(commentRepository.getById(1l)).thenReturn(foundComment);
        Mockito.when(commentRepository.save(modifyComment)).thenReturn(modifyComment);

        Comment updatedComment = commentService.update(modifyComment);

        Assertions.assertNotEquals(updatedComment.getComment(), COMMENT_HARIKA);
        Assertions.assertEquals(updatedComment.getComment(), COMMENT_ORTALAMA);

    }

}
