package com.spd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/**/*.js",
                        "/login",
                        "/**/*.js",
                        "/api/v1/users",
                        "/swagger*",
                        "/swagger-resources/**",
                        "/api-docs",
                        "/fonts/*",
                        "/v2/api-docs**",
                        "/webjars/**",
                        "/images/*.*")
                            .permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                    .permitAll()
                .antMatchers("/api/v1/users/verify")
                    .permitAll()
                .anyRequest().authenticated();
    }

}