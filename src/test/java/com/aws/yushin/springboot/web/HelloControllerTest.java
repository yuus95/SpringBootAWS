package com.aws.yushin.springboot.web;

import com.aws.yushin.springboot.web.controller.HelloController;
import org.junit.jupiter.api.Test;	// {1}
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
class HelloControllerTset {

    @Autowired
    private MockMvc mvc;
    @Test
    public void hello_to_return() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }
    // JSON 테스트
    @Test
    public void hello_dto_test() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
        .param("name",name)
        .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }
}

//
//    @Test
//    public void 회원가입() throws Exception{
//        //given
//        Member member = new Member();
//        member.setName("kim");
//
//        //when
//        Long savedId = memberService.join(member);
//
//
//
//        //then
//        // em.flush();
//        //assertEquals(a,b) 객체 a와 b의 값이 같은지 확인
//        assertEquals(member,memberRepository.findOne(savedId));
//    }
