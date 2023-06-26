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
                .antMatchers(HttpMethod.GET, "**/restaurant/waiting/**").hasAnyAuthority(Role.ADMIN.toString())
                .antMatchers(HttpMethod.PUT , "**/restaurant/{id}").hasAnyAuthority(Role.ADMIN.toString())
                .antMatchers(HttpMethod.POST , "**/restaurant").hasAnyAuthority(Role.SELLER.toString())
                .antMatchers(HttpMethod.POST , "**/branch").hasAnyAuthority(Role.SELLER.toString())
                .antMatchers(HttpMethod.POST , "**/menu").hasAnyAuthority(Role.SELLER.toString())
                .antMatchers(HttpMethod.POST , "**/meal").hasAnyAuthority(Role.SELLER.toString())
                .antMatchers(HttpMethod.POST , "**/comment").hasAnyAuthority(Role.USER.toString())
                .and()
                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(customAuthenticationFilter());

        http.headers().frameOptions().sameOrigin();


//      http.authorizeRequests().antMatchers().permitAll();

//      http.authorizeRequests().anyRequest().authenticated(); // Obligation of authentication of all endpoints
//      http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)); // 401 will throw for missing token
//      http.authorizeRequests().anyRequest().permitAll();   // It throws SpringSecurity out of picture.
//
//      http.addFilter(customAuthenticationFilter);
//      http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

//      http.csrf().disable(); // cross-side request forgery

//      http.headers().frameOptions().sameOrigin(); // it solved to access denied issue on attempt to access h2 db
//      http.headers().frameOptions().disable(); // it also solved access issue to h2 but this is less securely than above

    }

    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter authenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        authenticationFilter.setFilterProcessesUrl(SLASH + LOGIN_PATH);
        return authenticationFilter;
    }
}
