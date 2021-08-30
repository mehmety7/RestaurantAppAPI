package com.finartz.restaurantapp.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityConfigTest {

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

    @WithUserDetails("ali@gmail.com")
    @Test
    public void givenAuthorizationRequestOnPrivateService_shouldSucceedWith200() throws Exception {
        mvc.perform(get("/restaurant/waiting").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithUserDetails("veli@gmail.com")
    @Test
    public void givenNoAuthorizationRequestOnPrivateService_shouldFailedWith403() throws Exception {
        mvc.perform(get("/restaurant/waiting").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    public void givenNoAuthenticationRequestOnPrivateService_shouldFailedWith403() throws Exception {
        mvc.perform(get("/restaurant/waiting").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

}
