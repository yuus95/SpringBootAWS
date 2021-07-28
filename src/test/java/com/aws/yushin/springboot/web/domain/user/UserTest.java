package com.aws.yushin.springboot.web.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class UserTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void cleanup(){
        userRepository.deleteAll();
    }

    @Test
    public void 회원가입성공() throws Exception{
        //given
        User save = userRepository.save(User.builder()
                .email("kkad45@naver.com")
                .password("1234")
                .build());


        //when
        Optional<User> byId = userRepository.findById(save.getId());

        //then
        assertThat(byId.get().getEmail()).isEqualTo("kkad45@naver.com");
    }


}