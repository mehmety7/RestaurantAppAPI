package com.finartz.restaurantapp.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.entity.RestaurantEntity;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EntityNotFoundExceptionTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    @Test
    public void givenNotFoundId_thenThrowEntityNotFoundException_whenGetSpecificException_() throws Exception {
        Long idParam = 100L;

        mvc.perform(get("/address/{id}", idParam)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityNotFoundException))
                .andExpect(result -> assertEquals("Not found Address with id:" + idParam, result.getResolvedException().getMessage()));
    }

    @Test
    public void givenBranchCreateRequestBelongToNotApprovedRestaurant_thenThrowInvalidStatusException_whenGetSpecificException_() throws Exception {
        RestaurantEntity restaurantEntity = RestaurantEntity.builder().id(2l).build();
        BranchCreateRequest branchCreateRequest = BranchCreateRequest.builder()
                .name("Branch")
                .restaurantId(2l)
                .addressCreateRequest(null)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(branchCreateRequest);

        mvc.perform(post("/branch")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isForbidden())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidStatusException))
                .andExpect(result -> assertEquals("The status of restaurant must be APPROVED by admin to create branch", result.getResolvedException().getMessage()));
    }



}
