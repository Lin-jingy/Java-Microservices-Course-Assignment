package org.ljy.common.util.jwt;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class preHandleFunc {
    static public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String,Object> map = new HashMap<>();
        // 获取请求头中令牌
        String token = request.getHeader("token");
        try {
            // 验证令牌
            JWTUtils.verify(token);
            return true;  // 放行请求

        } catch (SignatureVerificationException e) {
            log.error(e.toString());
            map.put("msg","无效签名！");
        }catch (TokenExpiredException e){
            log.error(e.toString());
            map.put("msg","token过期");
        }catch (AlgorithmMismatchException e){
            log.error(e.toString());
            map.put("msg","算法不一致");
        }catch (Exception e){
            log.error(e.toString());
            map.put("msg","token无效！");
        }
        map.put("state",false);  // 设置状态
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
