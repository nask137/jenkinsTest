package com.example.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class JwtProperties {

    /**
     * 用户生成jwt令牌相关配置
     */
    @Value( value ="${jwt.user-secret-key}")
    private String userSecretKey;
    @Value( value ="${jwt.user-ttl}")
    private long userTtl;
    @Value( value ="${jwt.user-token-name}")
    private String userTokenName;


}
