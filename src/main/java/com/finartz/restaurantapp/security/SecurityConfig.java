package com.finartz.restaurantapp.security;

import com.finartz.restaurantapp.filter.CustomAuthenticationFilter;
import com.finartz.restaurantapp.filter.CustomAuthorizationFilter;
import com.finartz.restaurantapp.model.enumerated.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.finartz.restaurantapp.model.constant.ConfigConstant.LOGIN_PATH;
import static com.finartz.restaurantapp.model.constant.ConfigConstant.SLASH;

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
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(SLASH + LOGIN_PATH, "**/refresh-token/**", "**/h2/**").permitAll()
                .antMatchers(HttpMethod.GET, "/restaurant/waiting/**").hasAnyAuthority(Role.ADMIN.toString())
                .antMatchers(HttpMethod.PUT , "/restaurant/{id}").hasAnyAuthority(Role.ADMIN.toString())
                .antMatchers(HttpMethod.POST , "/restaurant").hasAnyAuthority(Role.SELLER.toString())
                .antMatchers(HttpMethod.POST , "/branch").hasAnyAuthority(Role.SELLER.toString())
                .antMatchers(HttpMethod.POST , "/menu").hasAnyAuthority(Role.SELLER.toString())
                .antMatchers(HttpMethod.POST , "/meal").hasAnyAuthority(Role.SELLER.toString())
                .antMatchers(HttpMethod.POST , "/comment").hasAnyAuthority(Role.USER.toString())
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(customAuthenticationFilter())
                .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

        http.headers().frameOptions().sameOrigin();
    }

    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter authenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        authenticationFilter.setFilterProcessesUrl(SLASH + LOGIN_PATH);
        return authenticationFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
