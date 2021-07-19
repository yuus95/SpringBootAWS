package com.aws.yushin.springboot.web.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// 인터페이스 생성 뒤 extends JpaRepository<Entity 클래스,Pk타입> 선언
// 엔티티 클래스는 기본 Repository없이는 제대로 역할을 할 수가 없다.
public interface PostsRepository extends JpaRepository<Posts,Long> {
}
