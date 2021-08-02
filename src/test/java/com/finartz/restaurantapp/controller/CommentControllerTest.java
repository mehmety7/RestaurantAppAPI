package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.Branch;
import com.finartz.restaurantapp.model.Comment;
import com.finartz.restaurantapp.model.User;
import com.finartz.restaurantapp.service.CommentService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    private static final String URI_COMMENT = "/comment";
    private static final String COMMENT_HARIKA = "Harika";
    private static final String COMMENT_ORTALAMA = "Ortalama";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    private ObjectWriter objectWriter;

    @Before
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = mapper.writer().withDefaultPrettyPrinter();

    }

    @Test
    public void whenGetAllComment_thenReturnComment() throws Exception {

        User user = User.builder().build();

        Branch branch = Branch.builder().build();

        Comment comment = Comment.builder()
                .id(1L)
                .comment(COMMENT_HARIKA)
                .branch(branch)
                .user(user)
                .speedPoint(9)
                .tastePoint(8)
                .build();

        List<Comment> commentList = Arrays.asList(comment);

        Mockito.when(commentService.getAll()).thenReturn(commentList);

        mockMvc.perform(get(URI_COMMENT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].comment", Matchers.is(COMMENT_HARIKA)));

    }

    @Test
    public void whenGetCommentById_thenReturnComment() throws Exception {

        User user = User.builder().build();

        Branch branch = Branch.builder().build();

        Comment comment = Comment.builder()
                .id(1L)
                .comment(COMMENT_HARIKA)
                .branch(branch)
                .user(user)
                .speedPoint(9)
                .tastePoint(8)
                .build();

        Mockito.when(commentService.getById(1L)).thenReturn(comment);

        mockMvc.perform(get(URI_COMMENT +"/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("comment", Matchers.is(COMMENT_HARIKA)));

    }

    @Test
    public void whenCreateNewComment_thenReturnComment() throws Exception {

        User user = User.builder().build();

        Branch branch = Branch.builder().build();

        Comment comment = Comment.builder()
                .id(1L)
                .comment(COMMENT_HARIKA)
                .branch(branch)
                .user(user)
                .speedPoint(9)
                .tastePoint(8)
                .build();

        Mockito.when(commentService.create(comment)).thenReturn(comment);

        String requestJson = objectWriter.writeValueAsString(comment);

        mockMvc.perform(post(URI_COMMENT)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("comment", Matchers.is(COMMENT_HARIKA)));
    }

    @Test
    public void whenUpdateComment_thenReturnComment() throws Exception {

        User user = User.builder().build();

        Branch branch = Branch.builder().build();

        Comment comment = Comment.builder()
                .id(1L)
                .comment(COMMENT_HARIKA)
                .branch(branch)
                .user(user)
                .speedPoint(9)
                .tastePoint(8)
                .build();

        Comment modifyComment = Comment.builder().id(1l).comment(COMMENT_ORTALAMA).build();

        Mockito.when(commentService.create(comment)).thenReturn(comment);
        Mockito.when(commentService.update(modifyComment)).thenReturn(modifyComment);

        String requestJson1 = objectWriter.writeValueAsString(comment);
        String requestJson2 = objectWriter.writeValueAsString(modifyComment);

        mockMvc.perform(post(URI_COMMENT)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson1))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("comment", Matchers.is(COMMENT_HARIKA)))
                .andExpect(jsonPath("id", Matchers.is(1)));

        mockMvc.perform(put(URI_COMMENT)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("comment", Matchers.is(COMMENT_ORTALAMA)))
                .andExpect(jsonPath("id", Matchers.is(1)));
    }

    @Test
    public void whenDeleteComment_thenReturnComment() throws Exception {

        User user = User.builder().build();

        Branch branch = Branch.builder().build();

        Comment comment = Comment.builder()
                .id(1L)
                .comment(COMMENT_HARIKA)
                .branch(branch)
                .user(user)
                .speedPoint(9)
                .tastePoint(8)
                .build();

        Mockito.when(commentService.deleteById(1L)).thenReturn(comment);

        String requestJson = objectWriter.writeValueAsString(comment);

        mockMvc.perform(delete(URI_COMMENT + "/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk());
    }


}
