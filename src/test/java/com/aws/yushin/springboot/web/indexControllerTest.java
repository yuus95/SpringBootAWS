package com.aws.yushin.springboot.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class indexControllerTest {

    @Autowired
   private TestRestTemplate restTemplate;

    @Test
    public void  메인페이지로딩() throws Exception{
        //given
        String body = this.restTemplate.getForObject("/",String.class);
        //when

        //then
        Assertions.assertThat(body).contains("스프링부트로 시작하는 웹서비스");

    }



}