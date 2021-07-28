package com.aws.yushin.springboot.web.domain.user;

import com.aws.yushin.springboot.web.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

@EnableJpaAuditing
@Getter
@NoArgsConstructor  // 파라미터가 없는 기본생성자
@AllArgsConstructor  //모든 필드값을 파라미터로 받는 생성자 생성
@Builder
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100,nullable = false,unique = true)
    private String email;


    @Column(length = 300,nullable = false)
    private String password;


}
