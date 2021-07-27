package com.aws.yushin.springboot.service.posts;

import com.aws.yushin.springboot.web.domain.posts.Posts;
import com.aws.yushin.springboot.web.domain.posts.PostsRepository;
import com.aws.yushin.springboot.web.dto.PostsListResponseDto;
import com.aws.yushin.springboot.web.dto.PostsResponseDto;
import com.aws.yushin.springboot.web.dto.PostsSaveRequestDto;
import com.aws.yushin.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;


    /**
     * toEntity() : Dto --> Entity
     */
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));
        posts.update(requestDto.getTitle(),requestDto.getContent());

        return id;

    }
    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id " + id));
        return new PostsResponseDto(entity);
    }


    /**
     * 트랜잭션 범위는 유지하되 조회기능만 남겨두어 조회속도가 개선
     */
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // .map(posts -> new PostsListResponseDto(posts)) --> postsRepository결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDTo변환 -> List로 반환.
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));

        postsRepository.delete(posts); // deleteById를 활용해도됨.
    }


}
