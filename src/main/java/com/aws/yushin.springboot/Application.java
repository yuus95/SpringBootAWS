package com.aws.yushin.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


// 항상 프로젝트의 최상단에 위치
@EnableJpaAuditing // JPA Auditing 어노테이션들을 모두 활성화 할 수 있게 해준다.
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
