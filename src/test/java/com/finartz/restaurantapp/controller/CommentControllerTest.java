package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.dto.CommentDto;
import com.finartz.restaurantapp.model.request.create.CommentCreateRequest;
import com.finartz.restaurantapp.model.request.update.CommentUpdateRequest;
import com.finartz.restaurantapp.service.CommentService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(CommentController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CommentControllerTest {

    private static final String URI_COMMENT = "/comment";
    private static final String COMMENT_HARIKA = "Harika";
    private static final String COMMENT_ORTALAMA = "Ortalama";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @MockBean
    private UserDetailsService userDetailsService;

    private ObjectWriter objectWriter;

    @Before
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = mapper.writer().withDefaultPrettyPrinter();

    }

    @Test
    public void whenGetCommentById_thenReturnComment() throws Exception {

        CommentDto comment = CommentDto.builder()
                .id(1L)
                .comment(COMMENT_HARIKA)
                .speedPoint(9)
                .tastePoint(8)
                .build();

        Mockito.when(commentService.getComment(1L)).thenReturn(comment);

        mockMvc.perform(get(URI_COMMENT +"/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("comment", Matchers.is(COMMENT_HARIKA)));

    }

    @Test
    public void whenCreateNewComment_thenReturnComment() throws Exception {

        CommentDto comment = CommentDto.builder()
                .id(1L)
                .comment(COMMENT_HARIKA)
                .speedPoint(9)
                .tastePoint(8)
                .branchId(1L)
                .userId(1L)
                .build();

        CommentCreateRequest commentCreateRequest = CommentCreateRequest
                .builder()
                .comment(COMMENT_HARIKA)
                .speedPoint(9)
                .tastePoint(8)
                .userId(1L)
                .branchId(1L)
                .build();

        Mockito.when(commentService.createComment(commentCreateRequest)).thenReturn(comment);

        String requestJson = objectWriter.writeValueAsString(commentCreateRequest);

        mockMvc.perform(post(URI_COMMENT)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("comment", Matchers.is(COMMENT_HARIKA)));
    }

    @Test
    public void whenUpdateComment_thenReturnComment() throws Exception {

        CommentDto comment = CommentDto.builder()
                .id(1L)
                .comment(COMMENT_HARIKA)
                .speedPoint(9)
                .tastePoint(8)
                .branchId(1L)
                .userId(1L)
                .build();

        CommentCreateRequest commentCreateRequest = CommentCreateRequest
                .builder()
                .comment(COMMENT_HARIKA)
                .speedPoint(9)
                .tastePoint(8)
                .userId(1L)
                .branchId(1L)
                .build();

        CommentDto commentUpdate = CommentDto.builder().
                id(1l)
                .comment(COMMENT_ORTALAMA)
                .build();

        CommentUpdateRequest commentUpdateRequest = CommentUpdateRequest
                .builder()
                .comment(COMMENT_ORTALAMA)
                .build();

        Mockito.when(commentService.createComment(commentCreateRequest)).thenReturn(comment);
        Mockito.when(commentService.updateComment(1L, commentUpdateRequest)).thenReturn(commentUpdate);

        String requestJson1 = objectWriter.writeValueAsString(commentCreateRequest);
        String requestJson2 = objectWriter.writeValueAsString(commentUpdateRequest);

        mockMvc.perform(post(URI_COMMENT)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson1))
                .andExpect(status().isCreated());

        mockMvc.perform(put(URI_COMMENT + "/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("comment", Matchers.is(COMMENT_ORTALAMA)))
                .andExpect(jsonPath("id", Matchers.is(1)));
    }

    @Test
    public void whenDeleteComment_thenReturnComment() throws Exception {

        mockMvc.perform(delete(URI_COMMENT + "/" + anyLong())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
