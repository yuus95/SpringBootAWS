package com.aws.yushin.springboot.web.dto;


import com.aws.yushin.springboot.web.domain.posts.Posts;
import lombok.Getter;



/**
 * Entity의 필드 중 일부만 사용하므로  생성자 매개변수로 Entity를 받아 필드에 값을 넣는다.
 * Dto - Entity를 받아 처리
 */
@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String  content;
    private String  author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title=entity.getTitle();
        this.content = entity.getContent();
        this.author=entity.getAuthor();
    }

}
