package com.aws.yushin.springboot.springsecurity.config.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * Spring Security Fliter Chain을 사용한다는것을 명시해주기 위해
 * @EnableWebSecurity 어노테이션 적용
 *
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("USER")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/**").permitAll();

    }
}
