package com.aws.yushin.springboot.web.domain.member;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * JPA repo를 상속하면 IOC등록이 자동으로 된다.
 * Transation이 포함되어있다.
 */
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);


//    /**
//     * eager조회를 할 수 있게 해줌
//     */
//    @EntityGraph(attributePaths = "authorities")
//    Optional<Member> findOneWithAuthoritiesByUsername(String username);
}
