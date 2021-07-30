package com.aws.yushin.springboot.web.dto;






import lombok.*;

/**
 * 토큰 정보를 Response할떄 사용할 TokenDto
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {

    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;
}