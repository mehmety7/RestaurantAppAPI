package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;
import com.finartz.restaurantapp.service.BranchService;
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

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(BranchController.class)
@AutoConfigureMockMvc(addFilters = false)
public class BranchControllerTest {

    private static final String URI_BRANCH = "/branch";
    private static final String NAME_KRAL_SISLI = "Kral Burger Şişli";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BranchService branchService;

    @MockBean
    private UserDetailsService userDetailsService;

    private ObjectWriter objectWriter;

    @Before
    public void init() {
        // to-do : https://stackoverflow.com/questions/20504399/testing-springs-requestbody-using-spring-mockmvc
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    public void whenGetByBranchId_thenReturnBranch() throws Exception {

        BranchDto branch = BranchDto.builder()

                .name(NAME_KRAL_SISLI)
                .id(1L)
                .build();

        Mockito.when(branchService.getBranch(1L)).thenReturn(branch);

        mockMvc.perform(get(URI_BRANCH + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is(NAME_KRAL_SISLI)));

    }

    @Test
    public void whenGetByCountyId_thenReturnBranches() throws Exception {

        BranchDto branch = BranchDto
                .builder()
                .id(1L)
                .name(NAME_KRAL_SISLI)
                .menuId(1L)
                .restaurantId(1L)
                .build();

        Mockito.when(branchService.getBranches(1L)).thenReturn(Arrays.asList(branch));

        mockMvc.perform(get(URI_BRANCH + "/county?countyId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Matchers.is(NAME_KRAL_SISLI)));

    }

    @Test
    public void whenCreateNewBranch_thenReturnCreated() throws Exception {

        BranchDto branch = BranchDto
                .builder()
                .id(1l)
                .name(NAME_KRAL_SISLI)
                .restaurantId(1l)
                .menuId(1l)
                .build();

        BranchCreateRequest branchCreateRequest = BranchCreateRequest
                .builder()
                .name(NAME_KRAL_SISLI)
                .addressCreateRequest(null)
                .restaurantId(1l)
                .build();

        Mockito.when(branchService.createBranch(branchCreateRequest, "jwt")).thenReturn(branch);

        String requestJson = objectWriter.writeValueAsString(branchCreateRequest);

        mockMvc.perform(post(URI_BRANCH)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated());

    }

}
