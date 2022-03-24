package com.example.pieceart.security.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Set;

@Log4j2
@Component
public class JWTUtil {
    //secret key 설정
    private String secretKey="pxiDrU05JNX0miBwB2FvpE28V5gl0lIwLEn3sHOSgFk=";
//    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    //토큰 유효기간 설정 (test 1일)
    private long expire = 60 * 24 * 1;

    //토큰 생성하기
    public String generateToken(String sub, Set<String> roles){
        Claims claims = Jwts.claims().setSubject(sub);
        claims.put("role", roles);
        log.info(Date.from(ZonedDateTime.now().plusMinutes(expire).toInstant()));
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setClaims(claims)
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expire).toInstant()))
                .signWith(key)
                .compact();
    }
    //토큰 유효여부 체크 (true or false)
    public boolean validateToken(String jwtToken) {
            Jws<Claims> claims = getClaimsFromToken(jwtToken);
            log.info("-------validateToken---------");
            log.info(claims);
            log.info(claims.getBody().getExpiration());
            return claims.getBody().getExpiration().after(new Date());
    }

    //토큰 추출
    public String extractTokenFromHeader(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
            log.info("Authorization exist: "+authHeader);
            return authHeader.split("Bearer ")[1];
        }
        return null;
    }
    //claims 추출
    public Jws<Claims> getClaimsFromToken(String jwtToken){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken);
    }

}
