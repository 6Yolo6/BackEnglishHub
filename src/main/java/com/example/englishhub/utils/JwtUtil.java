package com.example.englishhub.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

/**
 * jwt工具类
 *
 * @author hahaha
 */
@Slf4j
@Configuration
public class JwtUtil {
    // 30 秒
//    private static long EXPIRATION_TIME = 1000 * 30;
    // 6 hour
    private static long EXPIRATION_TIME = 3600000 * 6;
    // 一天

//    private static String SECRET = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjY34DFDSSSd";// 秘钥
    // 使用Base64编码的密钥
    private static String SECRET = Base64.getEncoder().encodeToString("MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjY34DFDSSSd".getBytes());

    private static final String USER_ID = "id";


    /**
     * 生成jwtToken
     *
     * @param id
     * @return
     */
    public static String generateToken(String id) {
        HashMap<String, Object> map = new HashMap<>();
        // you can put any data in the map
        map.put(USER_ID, id);
        // 1. setClaims(map)：将map中的数据存储到Claims中
        // 2. setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))：设置过期时间
        // 3. signWith(SignatureAlgorithm.HS512, SECRET)：设置加密算法和密钥
        // 4. compact()：生成token
        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return token;
    }

    /**
     * 检查token是否即将过期
     * @param token
     * 60分钟
     */
    public static boolean isTokenExpiring(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        Date expiration = claims.getExpiration();
        long diff = expiration.getTime() - System.currentTimeMillis();
        // 60分钟
        if (diff < 1000 * 60 * 60) {
            log.info("token即将过期");
            return true;
        }
//        log.info("token未即将过期");
        return false;
    }

    /**
     * 校验jwtToken
     *
     * @param token
     * @return
     */
    // 优化验证逻辑
    public static String validateToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        return claims.get(USER_ID, String.class);
//        // 使用自定义异常
//        if (StringUtils.isBlank(token)) {
//            throw new JwtValidationException(ResultType.UNAUTHORIZED.getCode(), "Token为空");
//        }
//        try {
//            Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
//            return claims.get(USER_ID, String.class);
//        } catch (ExpiredJwtException e) {
//            throw new JwtValidationException(ResultType.AGAIN_LOGIN.getCode(), "Token已过期，请重新登录");
//        } catch (JwtException e) {
//            throw new JwtValidationException(ResultType.UNAUTHORIZED.getCode(), "无效的Token");
//        }
    }

    public static void main(String[] args) {
        String id = "hahaha15";

        String token = generateToken(id);
        System.out.println(token);

        //token = "eyJhbGciOiJIUzUxMiJ9.eyJpZCI6IjY4NzZhYjFmYjk0MmZkNGYyN2Zm";
        id = validateToken(token);
        System.out.println(id);
//        HashMap<String, Object> map = new HashMap<>();
//        // you can put any data in the map
//        map.put("name", id);
//        token = Jwts.builder().setClaims(map).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
//
//         String result = "";
//        try {
//
//            result = validateToken(token);
//            System.out.println(result);
//        }catch (Exception e){
//            System.out.println(e.toString());
//        }

    }
}