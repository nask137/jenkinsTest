package com.example.controller;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.constant.JwtClaimsConstant;
import com.example.context.BaseContext;
import com.example.pojo.Result;
import com.example.pojo.entity.User;
import com.example.properties.JwtProperties;
import com.example.service.IUserService;
import com.example.utils.JwtUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author nask137
 * @since 2024-08-02
 */
@RestController
@Slf4j
@RequestMapping("/users")
@Data
public class UserController {

    private final IUserService userService;
    private final JwtProperties jwtProperties;

@GetMapping("/login")
    public Result<String > login(@RequestParam String openId){

    Long id = IdWorker.getId();
    User user = new User(id,null,"用户"+id%10000,null,openId);
    userService.save(user);
    Map<String, Object> claims = new HashMap<>();
    claims.put(JwtClaimsConstant.USER_ID, id);
    String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
    return Result.success(token);
    }
@PutMapping
    public Result<String> update(@RequestBody User user){
    user.setId(BaseContext.getCurrentId());
    log.info(BaseContext.getCurrentId().toString());
    userService.updateById(user);
    return Result.success("修改成功!");
}
}
