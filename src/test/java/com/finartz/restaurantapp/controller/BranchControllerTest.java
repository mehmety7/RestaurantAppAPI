package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.dto.BranchDto;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.model.request.create.BranchCreateRequest;
import com.finartz.restaurantapp.model.request.update.BranchUpdateRequest;
import com.finartz.restaurantapp.service.BranchService;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(BranchController.class)
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
                .status(Status.WAITING)
                .build();

        Mockito.when(branchService.getBranch(1L)).thenReturn(branch);

        mockMvc.perform(get(URI_BRANCH + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is(NAME_KRAL_SISLI)));

    }

    @Test
    public void whenCreateNewBranch_thenReturnCreated() throws Exception {

        BranchDto branch = BranchDto.builder().build();

        BranchCreateRequest branchCreateRequest = BranchCreateRequest.builder().build();

        Mockito.when(branchService.createBranch(branchCreateRequest)).thenReturn(branch);

        String requestJson = objectWriter.writeValueAsString(branch);

        mockMvc.perform(post(URI_BRANCH)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated());

    }

    @Test
    public void whenUpdateExistsBranch_thenReturnUpdated() throws Exception {

        BranchDto branch = BranchDto.builder()
                .id(1L)
                .status(Status.WAITING)
                .build();

        BranchDto branchUpdate = BranchDto.builder()
                .id(1L)
                .status(Status.APPROVED)
                .build();

        BranchCreateRequest branchCreateRequest = BranchCreateRequest.builder()
                .status(Status.WAITING)
                .build();

        BranchUpdateRequest branchUpdateRequest = BranchUpdateRequest.builder()
                .status(Status.APPROVED)
                .build();

        Mockito.when(branchService.createBranch(branchCreateRequest)).thenReturn(branch);
        Mockito.when(branchService.updateBranch(1L, branchUpdateRequest)).thenReturn(branchUpdate);

        String requestJson1 = objectWriter.writeValueAsString(branch);
        String requestJson2 = objectWriter.writeValueAsString(branchUpdateRequest);

        mockMvc.perform(post(URI_BRANCH)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson1))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("status", Matchers.is(Status.WAITING.toString())))
                .andExpect(jsonPath("id", Matchers.is(1)));

        mockMvc.perform(put(URI_BRANCH + "/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status", Matchers.is(Status.APPROVED.toString())))
                .andExpect(jsonPath("id", Matchers.is(1)));

    }

    @Test
    public void whenGetWaitingBranches_thenReturnWaitingBranches() throws Exception {


        BranchDto branch = BranchDto.builder().status(Status.WAITING).build();

        List<BranchDto> branches = Arrays.asList(branch);

        Mockito.when(branchService.getBranches(Status.WAITING)).thenReturn(branches);

        mockMvc.perform(get(URI_BRANCH + "/waiting")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)));

    }

}
