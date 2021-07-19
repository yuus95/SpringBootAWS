package com.aws.yushin.springboot.web.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest
class HelloResponseDtoTest {

    @Test
    public void Test(){
        String name = "hello";
        int amount = 10;
        HelloResponseDto dto = new HelloResponseDto(name,amount);
        name = "hello2";
        assertThat(dto.getName()).isEqualTo(name);

    }


}