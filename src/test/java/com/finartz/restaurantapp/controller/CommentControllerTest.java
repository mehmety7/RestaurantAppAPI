package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.Branch;
import com.finartz.restaurantapp.model.Comment;
import com.finartz.restaurantapp.model.User;
import com.finartz.restaurantapp.service.CommentService;
import org.hamcrest.Matchers;
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

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Test
    public void whenGetAllComment_thenReturnComment() throws Exception {

        User user = User.builder().build();

        Branch branch = Branch.builder().build();

        Comment comment = Comment.builder()
                .id(1L)
                .comment("Harika")
                .branch(branch)
                .user(user)
                .speedPoint(9)
                .tastePoint(8)
                .build();

        List<Comment> commentList = Arrays.asList(comment);

        Mockito.when(commentService.getAll()).thenReturn(commentList);

        mockMvc.perform(get("/comment")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].comment", Matchers.is("Harika")));

    }

    @Test
    public void whenGetCommentById_thenReturnComment() throws Exception {

        User user = User.builder().build();

        Branch branch = Branch.builder().build();

        Comment comment = Comment.builder()
                .id(1L)
                .comment("Harika")
                .branch(branch)
                .user(user)
                .speedPoint(9)
                .tastePoint(8)
                .build();

        Mockito.when(commentService.getById(1L)).thenReturn(comment);

        mockMvc.perform(get("/comment/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("comment", Matchers.is("Harika")));

    }

    @Test
    public void whenCreateNewComment_thenReturnComment() throws Exception {

        User user = User.builder().build();

        Branch branch = Branch.builder().build();

        Comment comment = Comment.builder()
                .id(1L)
                .comment("Harika")
                .branch(branch)
                .user(user)
                .speedPoint(9)
                .tastePoint(8)
                .build();

        Mockito.when(commentService.create(comment)).thenReturn(comment);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(comment);

        mockMvc.perform(post("/comment")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("comment", Matchers.is("Harika")));
    }

    @Test
    public void whenUpdateComment_thenReturnComment() throws Exception {

        User user = User.builder().build();

        Branch branch = Branch.builder().build();

        Comment comment = Comment.builder()
                .id(1L)
                .comment("Harika")
                .branch(branch)
                .user(user)
                .speedPoint(9)
                .tastePoint(8)
                .build();

        comment.setComment("Ortalama");

        Mockito.when(commentService.update(comment)).thenReturn(comment);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(comment);

        mockMvc.perform(put("/comment")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("comment", Matchers.is("Ortalama")));
    }

    @Test
    public void whenDeleteComment_thenReturnComment() throws Exception {

        User user = User.builder().build();

        Branch branch = Branch.builder().build();

        Comment comment = Comment.builder()
                .id(1L)
                .comment("Harika")
                .branch(branch)
                .user(user)
                .speedPoint(9)
                .tastePoint(8)
                .build();

        Mockito.when(commentService.deleteById(1L)).thenReturn(comment);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(comment);

        mockMvc.perform(delete("/comment/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk());
    }


}
