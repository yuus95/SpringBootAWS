package com.aws.yushin.springboot.web.make_test;

import com.aws.yushin.springboot.web.domain.posts.Posts;
import com.aws.yushin.springboot.web.domain.posts.PostsRepository;
import com.aws.yushin.springboot.web.dto.PostsSaveRequestDto;
import com.aws.yushin.springboot.web.dto.PostsUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class test1 {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @BeforeEach
    public void 기본데이터() throws Exception{
        //given
        String title= "1";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("yushin")
                .build());
    }

    @AfterEach
    public void 데이터삭제() throws Exception{
        postsRepository.deleteAll();
    }


    @Test
    public void 조회테스트() throws Exception{
        Long id = 1L;
        String url = "http://localhost:"+port+"/api/v1/posts/"+id;

        //when
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(url, String.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).contains("yushin");
    }


//    	public ResponseEntity(@Nullable T body, @Nullable MultiValueMap<String, String> headers, HttpStatus status) {
    @Test
    public void 메인_findAll() throws Exception{
        //given
        String url = "http://localhost:"+ + port;

        //when
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(url, String.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).contains("yushin");

    }

    //	public ResponseEntity(@Nullable T body, @Nullable MultiValueMap<String, String> headers, HttpStatus status) {
    @Test
    public void  게시글등록() throws Exception{
        //given
        PostsSaveRequestDto saveDto = PostsSaveRequestDto.builder()
                .title("유신")
                .author("유신작가")
                .content("게시글등록")
                .build();

        String url = "http://localhost:"+ port + "/api/v1/posts";


        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, saveDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(2L);

        Optional<Posts> byId = postsRepository.findById(2L);
        assertThat(byId.get().getTitle()).isEqualTo("유신");


    }


    @Test
    public void 수정하기() throws Exception{
        //given
        Posts savePosts = postsRepository.save(Posts.builder()
                .title("hello")
                .author("hello2")
                .content("수정테스트하기")
                .build());

        String changeTitle="유신테스트";
        String changeContent ="유신테스트컨텐츠";

        PostsUpdateRequestDto updateDto = PostsUpdateRequestDto.builder()
                .title(changeTitle)
                .content(changeContent)
                .build();

        String url = "http://localhost:" + port +"api/v1/posts/"+savePosts.getId();

        HttpEntity<PostsUpdateRequestDto> dtoHttpEntity = new HttpEntity<>(updateDto);
        /**
         *public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity <T>  requestEntity,
         * 			Class<T> responseType, Object... urlVariables
         */
        //when
        ResponseEntity<Long> exchange = testRestTemplate.exchange(url, HttpMethod.PUT, dtoHttpEntity, Long.class);

        //then

        // http 상태코드 학인
        assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);


        // 데이터 변경 잘됐는지 확인
        Optional<Posts> byId = postsRepository.findById(savePosts.getId());
        assertThat(byId.get().getTitle()).isEqualTo("유신테스트");
    }



}
