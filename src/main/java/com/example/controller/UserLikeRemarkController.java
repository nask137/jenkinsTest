package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.context.BaseContext;
import com.example.pojo.Result;
import com.example.pojo.entity.UserLikeRemark;
import com.example.service.IUserLikeRemarkService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户点赞二级评论表 前端控制器
 * </p>
 *
 * @author nask137
 * @since 2024-08-02
 */
@RestController
@Data
@RequestMapping("/user-like-remark")
public class UserLikeRemarkController {
private  final IUserLikeRemarkService userLikeRemarkService;

    @PutMapping
    public Result<String> like(@RequestParam Long id){
        UserLikeRemark userLikeRemark = new UserLikeRemark();
        userLikeRemark.setRemarkId(id);
        userLikeRemark.setUserId(BaseContext.getCurrentId());
        userLikeRemarkService.save(userLikeRemark);
        return  Result.success("点赞成功!");
    }
    @DeleteMapping
    public Result<String> unLike(@RequestParam Long id){
        LambdaQueryWrapper<UserLikeRemark> userLikeRemarkLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLikeRemarkLambdaQueryWrapper.eq(UserLikeRemark::getUserId,BaseContext.getCurrentId());
        userLikeRemarkLambdaQueryWrapper.eq(UserLikeRemark::getRemarkId,id);
        UserLikeRemark one = userLikeRemarkService.getOne(userLikeRemarkLambdaQueryWrapper);
        if(one!=null)userLikeRemarkService.removeById(one.getId());
        else return  Result.error("操作失败!");
        return Result.success("取消成功!");
    }
}
