package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.context.BaseContext;
import com.example.pojo.Result;
import com.example.pojo.entity.Comment;
import com.example.pojo.entity.Remark;
import com.example.pojo.vo.CommentVo;
import com.example.service.ICommentService;
import com.example.service.IRemarkService;
import com.example.service.IUserLikeCommentService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 * 一级评论 前端控制器
 * </p>
 *
 * @author nask137
 * @since 2024-08-02
 */
@RestController
@RequestMapping("/comments")
@Data
public class CommentController {
    private final ICommentService commentService;
    private final IUserLikeCommentService userLikeCommentService;
    private final IRemarkService remarkService;

    @PostMapping
    public Result<String> addComment(@RequestBody Comment comment) {
        comment.setTime(LocalDateTime.now());
        comment.setUserId(BaseContext.getCurrentId());
        commentService.save(comment);
        return Result.success("评论成功!");
    }
    @DeleteMapping
    public Result<String> delete(@RequestParam Long id){
        Comment one = commentService.getById(id);
        if(!Objects.equals(one.getUserId(), BaseContext.getCurrentId()))return Result.error("非本人评论!");
        LambdaQueryWrapper<Remark> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Remark::getCommentId,id);
        remarkService.remove(lambdaQueryWrapper);
        commentService.removeById(id);
        return Result.success("删除成功!");
    }
    @GetMapping
    public Result<Page<CommentVo>>pageComments(@RequestParam Long id, @RequestParam Integer page, @RequestParam Integer size){
        Page<Comment> commentPage = new Page<>(page, size);
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        commentLambdaQueryWrapper.eq(Comment::getPostId,id);
        Page<Comment> result = commentService.page(commentPage,commentLambdaQueryWrapper);
        return Result.success(userLikeCommentService.isLike(result));
    }

}
