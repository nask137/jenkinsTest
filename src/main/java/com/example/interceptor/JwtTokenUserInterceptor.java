package com.example.interceptor;
import com.example.constant.JwtClaimsConstant;
import com.example.context.BaseContext;
import com.example.exception.CustomException;
import com.example.properties.JwtProperties;
import com.example.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
@Data
public class JwtTokenUserInterceptor implements HandlerInterceptor {

    private final JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        //当前拦截到的不是动态方法，直接放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 获取请求路径
        String requestUri = request.getRequestURI();

        // 如果请求路径中包含 /login，直接放行
        if (requestUri.contains("/login")) {
            return true;
        }
        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getUserTokenName());
        //2、校验令牌
        if (token == null) {
            throw new CustomException("请登录!");
        }
        Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
        Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
        BaseContext.setCurrentId(userId);
        return true;
    }
}