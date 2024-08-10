package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.context.BaseContext;
import com.example.pojo.Result;
import com.example.pojo.entity.UserLikePost;
import com.example.service.IUserLikePostService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author nask137
 * @since 2024-08-03
 */
@RestController
@Data
@RequestMapping("/user-like-post")
public class UserLikePostController {
    private final IUserLikePostService userLikePostService;

    @PutMapping
    public Result<String> like(@RequestParam Long id) {
        UserLikePost userLikePost = new UserLikePost();
        userLikePost.setPostId(id);
        userLikePost.setUserId(BaseContext.getCurrentId());
        userLikePostService.save(userLikePost);
        return Result.success("点赞成功!");
    }

    @DeleteMapping
    public Result<String> unLike(@RequestParam Long id) {
        LambdaQueryWrapper<UserLikePost> userLikePostLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLikePostLambdaQueryWrapper.eq(UserLikePost::getUserId, BaseContext.getCurrentId());
        userLikePostLambdaQueryWrapper.eq(UserLikePost::getPostId, id);
        UserLikePost one = userLikePostService.getOne(userLikePostLambdaQueryWrapper);
        if (one != null) userLikePostService.removeById(one.getId());
        else return Result.error("操作失败!");
        return Result.success("取消成功!");
    }
}
