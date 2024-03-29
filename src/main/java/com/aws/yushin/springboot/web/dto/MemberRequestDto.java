package com.aws.yushin.springboot.web.dto;


import com.aws.yushin.springboot.web.domain.member.Authority;
import com.aws.yushin.springboot.web.domain.member.Member;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    @Max(20)
    private String email;
    @NotBlank
    private String password;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}