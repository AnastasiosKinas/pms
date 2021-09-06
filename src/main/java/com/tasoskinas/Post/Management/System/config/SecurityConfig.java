package com.tasoskinas.Post.Management.System.config;

import com.tasoskinas.Post.Management.System.enums.Role;
import com.tasoskinas.Post.Management.System.filters.JwtRequestFilter;
import com.tasoskinas.Post.Management.System.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserServiceImpl myUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/authenticate")
            .permitAll()
            .antMatchers("/api/users", "/api/users/**")
            .hasAnyAuthority(Role.ADMIN.toString())
            .antMatchers(HttpMethod.POST,
                "/api/file",
                "/api/file/**",
                "/api/categories",
                "/api/categories/**",
                "/api/posts",
                "/api/posts/**")
            .hasAnyAuthority(Role.ADMIN.toString())
            .antMatchers(HttpMethod.PUT,
                "/api/file",
                "/api/file/**",
                "/api/categories",
                "/api/categories/**",
                "/api/posts",
                "/api/posts/**")
            .hasAnyAuthority(Role.ADMIN.toString())
            .antMatchers(HttpMethod.DELETE,
                "/api/file",
                "/api/file/**",
                "/api/categories",
                "/api/categories/**",
                "/api/posts",
                "/api/posts/**")
            .hasAnyAuthority(Role.ADMIN.toString())
            .antMatchers(HttpMethod.GET,
                "/api/file",
                "/api/file/**",
                "/api/categories",
                "/api/categories/**",
                "/api/posts",
                "/api/posts/**")
            .hasAnyAuthority(Role.ADMIN.toString(), Role.SIMPLE_USER.toString())
            .antMatchers("/api/comments", "/api/comments/**")
            .hasAnyAuthority(Role.ADMIN.toString(), Role.SIMPLE_USER.toString())
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
