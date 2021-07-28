package com.aws.yushin.springboot.springsecurity.config.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.List;


@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    //비밀키
    private String seretKey="yushin";

    // 토큰 유효시간 30분
    private long tokenValidTime = 30 * 60 * 1000L;

    //시큐리티 관련 생성자?
    private final UserDetailsService userDetailsService;


    //객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init(){
        seretKey = Base64.getEncoder().encodeToString(seretKey.getBytes());
    }

    // JWT 토큰 생성
    public String createToken(String userPk, List<String> roles){
        Claims claims = Jwts.claims().setSubject(userPk); // Jwt payload에 저장되는 정보단위
        claims.put("roles",roles); //정보는 key , value 쌍으로저장
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+tokenValidTime))
//                .signWith(seretKey,SignatureAlgorithm.HS256) 집가서다시하기
                .compact();

    }


}
