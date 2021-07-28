package com.aws.yushin.springboot.web;

import com.aws.yushin.springboot.web.domain.posts.Posts;
import com.aws.yushin.springboot.web.domain.posts.PostsRepository;
import com.aws.yushin.springboot.web.dto.PostsResponseDto;
import com.aws.yushin.springboot.web.dto.PostsSaveRequestDto;
import com.aws.yushin.springboot.web.dto.PostsUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;



/**
 * webEnviroment 속성 
 * Mock : 내장 톰켓 구동x
 * RANDOM_PORT_DEFINED_PORT :내장 톰켓 사용
 */ 
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) 
class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public void Post등록() throws Exception{
        //given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:"+port+"/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity= restTemplate.postForEntity(url, requestDto, Long.class);

        //then 
        
        // http 응답상태 확인
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        
        //JPA데이터확인
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);


    }


    @Test
    public void Posts수정() throws Exception{
        //given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savedPosts.getId();

        String expectedTitle = "title2";
        String expectedContent="content2";

        PostsUpdateRequestDto requestDto =
                PostsUpdateRequestDto.builder()
                        .title(expectedTitle)
                        .content(expectedContent)
                        .build();
        String url = "http://localhost:"+port+"/api/v1/posts/"+updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);


        /**
         *public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity <T>  requestEntity,
         * 			Class<T> responseType, Object... urlVariables
         */
        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);


        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }

    @Test
    public void 조회코드() throws Exception{
        //given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        Long id = postsRepository.save(requestDto.toEntity()).getId();
        String url = "http://localhost:"+port+"/api/v1/posts/"+id;

        Optional<Posts> byId = postsRepository.findById(id);
        PostsResponseDto postsResponseDto = new PostsResponseDto(byId.orElse(null));
        HttpEntity<PostsResponseDto> requestEntity = new HttpEntity<>(postsResponseDto);

        //when
        ResponseEntity<Long> forEntity = restTemplate.getForEntity(url,Long.class);


        //then
        assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(forEntity.getBody()).isGreaterThan(0L);

        assertThat(byId.get().getTitle()).isEqualTo(title);

    }




}