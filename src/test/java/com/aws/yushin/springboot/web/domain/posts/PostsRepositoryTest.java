package com.aws.yushin.springboot.web.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
        public void 게시글저장_불러오기() throws Exception{
            //given
            String title= "테스트 게시글";
            String content = "테스트 본문";

            postsRepository.save(Posts.builder()
            .title(title)
            .content(content)
            .author("yushin")
            .build());

            //when
            List<Posts> postsList = postsRepository.findAll();

            //then
            Posts posts = postsList.get(0);
            assertThat(posts.getTitle()).isEqualTo(title);
            assertThat(posts.getContent()).isEqualTo(content);

        }



}