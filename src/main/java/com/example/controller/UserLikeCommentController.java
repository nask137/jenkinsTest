package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.context.BaseContext;
import com.example.pojo.Result;
import com.example.pojo.entity.UserLikeComment;
import com.example.service.IUserLikeCommentService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 哟ing胡点赞了那些评论 前端控制器
 * </p>
 *
 * @author nask137
 * @since 2024-08-02
 */
@RestController
@Data
@RequestMapping("/user-like-comment")
public class UserLikeCommentController {
private final IUserLikeCommentService userLikeCommentService;
    @PutMapping
    public Result<String> like(@RequestParam Long id){
        UserLikeComment userLikeComment = new UserLikeComment();
        userLikeComment.setCommentId(id);
        userLikeComment.setUserId(BaseContext.getCurrentId());
        userLikeCommentService.save(userLikeComment);
        return  Result.success("点赞成功!");
    }
    @DeleteMapping
    public  Result<String> unLike(@RequestParam Long id){
        LambdaQueryWrapper<UserLikeComment> userLikeCommentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLikeCommentLambdaQueryWrapper.eq(UserLikeComment::getUserId,BaseContext.getCurrentId());
        userLikeCommentLambdaQueryWrapper.eq(UserLikeComment::getCommentId,id);
        UserLikeComment one = userLikeCommentService.getOne(userLikeCommentLambdaQueryWrapper);
        if(one!=null)userLikeCommentService.removeById(one.getId());
        else return Result.error("操作失败!");
        return Result.success("取消成功!");
    }
}
