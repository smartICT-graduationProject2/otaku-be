package com.otaku.otakube.common.security.jwt;


import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.jwt.JwtCustomException;
import com.otaku.otakube.common.security.consts.AuthConst;
import com.otaku.otakube.common.security.helper.AuthInfo;
import com.otaku.otakube.utils.serializer.AuthInfoSerializer;
import com.otaku.otakube.entity.user.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final RedisTemplate<String, String> redisTemplate;
    private final AuthInfoSerializer authInfoSerializer;
    @Value("${auth.jwt.secret}")
    private String secret;
    private Key key;

    //TODO: 개발용으로 시간을 길게 설정하였으니 나중에 수정 필요
    public static long ACCESS_TOKEN_PERIOD = 1000 * 60 * 60 * 24; //24시간
    public static long REFRESH_TOKEN_PERIOD = 1000 * 60 * 60 * 24 * 3; //3일

    @PostConstruct
    protected void init() {
        String encodedKey = Base64.getEncoder().encodeToString(secret.getBytes());
        key = Keys.hmacShaKeyFor(encodedKey.getBytes());
    }

    private JwtBuilder buildToken(final Date now) {
        return Jwts.builder()
                .setIssuedAt(now)
                .signWith(key);
    }

    public String generateAccessToken(final Long id, final Role role) {
        final Date now = new Date();

        return buildToken(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_PERIOD))
                .setSubject(String.valueOf(id))
                .claim(AuthConst.ROLE, role.toString())
                .compact();
    }

    public String generateRefreshToken(final Long id, final Role role) {
        final Date now = new Date();
        final String uuid = UUID.randomUUID().toString();
        final String generatedRefreshToken = buildToken(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_PERIOD))
                .setId(uuid) // 리프레시 토큰의 무작위성을 보장
                // REDIS에 key 값으로 토큰을 넣고 value 값으로 id와 role을 넣어서 찾기
                .compact();

        final AuthInfo authInfo = AuthInfo.builder().id(id).role(role).build();

        redisTemplate.opsForValue().set(generatedRefreshToken, authInfoSerializer.serialize(authInfo), ACCESS_TOKEN_PERIOD, TimeUnit.MILLISECONDS);

        return generatedRefreshToken;
    }

    public void validateToken(final String token) {
        final JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
        try {
            parser.parse(token);
        } catch (MalformedJwtException | IllegalArgumentException e) {
            throw JwtCustomException.of(ErrorDetails.INVALID_TOKEN);
        } catch (ExpiredJwtException e) {
            throw JwtCustomException.of(ErrorDetails.EXPIRED_TOKEN);
        }
    }

    public void validateRefreshToken(final String refreshToken) {
        final JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
        try {
            parser.parse(refreshToken);
        } catch (MalformedJwtException | IllegalArgumentException e) {
            throw JwtCustomException.of(ErrorDetails.INVALID_REFRESH_TOKEN);
        } catch (ExpiredJwtException e) {
            throw JwtCustomException.of(ErrorDetails.EXPIRED_REFRESH_TOKEN);
        }
    }

    public AuthInfo extractAuthenticationInfoFromToken(final String token) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        final Long id = Long.parseLong(claims.getSubject());
        final String role = claims.get(AuthConst.ROLE, String.class);

        return AuthInfo.builder()
                .id(id)
                .role(Role.valueOf(role))
                .build();
    }

    //재발행 로직
    public String reIssueAccessToken(final String refreshToken) {
        validateRefreshToken(refreshToken);
        final AuthInfo authInfo = getAuthenticationInfoFromRedis(refreshToken);

        return generateAccessToken(authInfo.getId(), authInfo.getRole());
    }

    public String reIssueRefreshToken(final String refreshToken) {
        validateRefreshToken(refreshToken);
        final AuthInfo authInfo = getAuthenticationInfoFromRedis(refreshToken);
        redisTemplate.delete(refreshToken);

        return generateRefreshToken(authInfo.getId(), authInfo.getRole());
    }

    private AuthInfo getAuthenticationInfoFromRedis(final String refreshToken) {
        final String authenticationInfo = Optional.ofNullable(redisTemplate.opsForValue().get(refreshToken)).orElseThrow(
                () -> JwtCustomException.of(ErrorDetails.INVALID_REFRESH_TOKEN) // 리프레시 토큰이 저장소에 존재하지 않을 경우 Error 리턴
        );

        return authInfoSerializer.deserialize(authenticationInfo);
    }
}
