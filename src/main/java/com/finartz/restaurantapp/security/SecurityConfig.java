package com.finartz.restaurantapp.security;

import com.finartz.restaurantapp.exception.GlobalSecurityExceptionHandler;
import com.finartz.restaurantapp.filter.CustomAuthenticationFilter;
import com.finartz.restaurantapp.filter.CustomAuthorizationFilter;
import com.finartz.restaurantapp.model.enumerated.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/login"); // login url can change with this line

        http.authorizeRequests().antMatchers("/login/**", "/user/refresh-token/**", "/h2/**").permitAll();
        http.authorizeRequests().antMatchers("/restaurant/waiting/**").hasAnyAuthority(Role.ADMIN.toString());
        http.authorizeRequests().antMatchers(HttpMethod.PUT , "/restaurant/{id}").hasAnyAuthority(Role.ADMIN.toString());
        http.authorizeRequests().antMatchers(HttpMethod.POST , "/restaurant").hasAnyAuthority(Role. SELLER.toString());
        http.authorizeRequests().antMatchers(HttpMethod.POST , "/comment").hasAnyAuthority(Role. USER.toString());

//      http.authorizeRequests().anyRequest().authenticated(); // Obligation of authentication of all endpoints
//      http.authorizeRequests().anyRequest().permitAll();   // It throws SpringSecurity out of picture.

        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable(); // cross-side request forgery
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.headers().frameOptions().sameOrigin(); // it solved to access denied issue on attempt to access h2 db
//      http.headers().frameOptions().disable(); // it also solved access issue to h2 but this is less securely than above

//      http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new GlobalSecurityExceptionHandler();
    }
}
