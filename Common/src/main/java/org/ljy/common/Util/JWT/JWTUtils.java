package org.ljy.common.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import org.ljy.common.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JWTUtils {

    private final static String SIGN = "abcdef";


    public static String getToken(User user) {

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 1);

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        // payload
        Map<String, String> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("username", user.getUsername());
        map.put("permission", user.getPermission());
        map.forEach(builder::withClaim);
        return builder.withExpiresAt(instance.getTime())  //指定令牌过期时间
                .sign(Algorithm.HMAC256(SIGN));
    }

    public static Map<String, String> verify(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SIGN))
                    .build()
                    .verify(token);

            return decodedJWT.getClaims().entrySet().stream()
                    .filter(entry -> entry.getKey() != null && entry.getValue().asString() != null) // 过滤null键或null值
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> entry.getValue().asString()
                    ));
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token 验证失败", e);
        }
    }
}
