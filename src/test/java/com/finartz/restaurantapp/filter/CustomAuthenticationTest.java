package com.finartz.restaurantapp.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.finartz.restaurantapp.model.constant.ConfigConstant;
import com.finartz.restaurantapp.model.enumerated.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.banner-mode=off")
@ActiveProfiles("test")
public class CustomAuthenticationTest {

    private CustomAuthenticationFilter customAuthenticationFilter;

    @Autowired
    private AuthenticationManager authenticationManager;

    private Authentication authentication;
    private final MockHttpServletRequest request = new MockHttpServletRequest("GET", "/login");
    private final MockHttpServletResponse response = new MockHttpServletResponse();
    private MockFilterChain filterChain;

    @Before
    public void init(){

        customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager);

        request.addParameter("email", "ali@gmail.com");
        request.addParameter("password", "ali1212");
        filterChain = new MockFilterChain();

        authentication = customAuthenticationFilter.attemptAuthentication(request, response);

    }

    @Test
    public void whenPassValidRequest_thenReturnAuthentication(){

        List<Role> roles = Collections.singletonList(Role.ADMIN);
        List<String> authorities = roles.stream().map(Role::name).collect(Collectors.toList());
        List<String> result = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        Assertions.assertEquals(result, authorities);

    }

    @Test
    public void whenSuccessfulAuthentication_thenSetResponseWithNewToken() {

        customAuthenticationFilter.successfulAuthentication(request, response, filterChain, authentication);

        Algorithm algorithm = Algorithm.HMAC256(ConfigConstant.SECRET.getBytes());
        int ACCESS_TOKEN_MINUTE = 60;
        String accessToken = JWT.create()
                .withSubject("ali@gmail.com")
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_MINUTE * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim(ConfigConstant.ROLES, authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        int REFRESH_TOKEN_MINUTE = 60 * 24 * 15;
        String refreshToken = JWT.create()
                .withSubject("ali@gmail.com")
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_MINUTE * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

        Assertions.assertEquals(response.getHeader(ConfigConstant.REFRESH_TOKEN), refreshToken);
        Assertions.assertEquals(response.getHeader(ConfigConstant.ACCESS_TOKEN), accessToken);

    }
}
