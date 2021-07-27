package com.aws.yushin.springboot.web.make_test;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class common {

    @Autowired
    protected MockMvc mockMvc;


    /**
     * 객체를 json으로 변환하거나 그 반대에 사용한다. 여기서는 객체를 json문자열로 바꿔서 body에실어서 보내는데 사용
     */
    @Autowired
    protected ObjectMapper objectMapper;
}
