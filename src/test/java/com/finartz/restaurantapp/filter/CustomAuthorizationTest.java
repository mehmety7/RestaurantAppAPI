package com.finartz.restaurantapp.filter;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.finartz.restaurantapp.model.enumerated.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@ActiveProfiles("test")
public class CustomAuthorizationTest {


    private CustomAuthorizationFilter customAuthorizationFilter;

    private MockHttpServletRequest request = new MockHttpServletRequest("POST", "/login");
    private MockHttpServletResponse response = new MockHttpServletResponse();
    private MockFilterChain filterChain = new MockFilterChain();
    private String accessToken;

    @Before
    public void init(){

        customAuthorizationFilter = new CustomAuthorizationFilter();

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        accessToken = JWT.create()
                .withSubject("ali@gmail.com")
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", Arrays.asList(Role.ADMIN.toString()))
                .sign(algorithm);

    }

    @Test
    public void givenURLIsLoginOrRefreshToken_whenDoFilterInternal_thenDoFilterOnly() throws ServletException, IOException {

        request.setServletPath("/login");
        customAuthorizationFilter.doFilterInternal(request, response, filterChain);

    }

    @Test
    public void givenRequestHasNotValidToken_whenDoFilterInternal_thenDoFilterOnly() throws ServletException, IOException {

        request.addHeader(HttpHeaders.AUTHORIZATION, "");
        customAuthorizationFilter.doFilterInternal(request, response, filterChain);

    }

    @Test
    public void givenRequestHasValidBearerToken_whenDoFilterInternal_thenSetAuthenticationAndAfterDoFilter() throws ServletException, IOException {

        accessToken = "Bearer " + accessToken;
        request.addHeader(HttpHeaders.AUTHORIZATION, accessToken);
        customAuthorizationFilter.doFilterInternal(request, response, filterChain);

    }

    @Test
    public void givenRequestHasInvalidBearerToken_whenDoFilterInternal_thenThrowException() throws ServletException, IOException {

        accessToken = "Bearer ";
        request.addHeader(HttpHeaders.AUTHORIZATION, accessToken);
        customAuthorizationFilter.doFilterInternal(request, response, filterChain);

        Assertions.assertEquals(response.getStatus(), FORBIDDEN.value());

    }


}
