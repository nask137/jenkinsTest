package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.context.BaseContext;
import com.example.pojo.Result;
import com.example.pojo.entity.Comment;
import com.example.pojo.entity.Remark;
import com.example.pojo.entity.UserLikeRemark;
import com.example.pojo.vo.RemarkVo;
import com.example.service.ICommentService;
import com.example.service.IRemarkService;
import com.example.service.IUserLikeRemarkService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 耳机评论 前端控制器
 * </p>
 *
 * @author nask137
 * @since 2024-08-02
 */
@RestController
@Data
@RequestMapping("/remarks")
public class RemarkController {
    private final IRemarkService remarkService;
    private final IUserLikeRemarkService userLikeRemarkService;
    private final ICommentService commentService;
    @PostMapping
    @Transactional
    public Result<String> saveRemark(@RequestBody Remark remark) {
        remark.setTime(LocalDateTime.now());
        remark.setUserId(BaseContext.getCurrentId());
        remarkService.save(remark);
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        commentLambdaQueryWrapper.eq(Comment::getId,remark.getCommentId());
        Comment one = commentService.getOne(commentLambdaQueryWrapper);

        one.setRemarkNum(one.getRemarkNum()+1);
        commentService.updateById(one);
        return Result.success("评论成功!");

    }

    @DeleteMapping
    @Transactional
    public Result<String> deleteRemark(@RequestParam Long id) {
        Remark one = remarkService.getById(id);

        if (!Objects.equals(one.getUserId(), BaseContext.getCurrentId())) return Result.error("非本人评论!");
        remarkService.removeById(id);
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        commentLambdaQueryWrapper.eq(Comment::getId,one.getCommentId());
        Comment comment = commentService.getOne(commentLambdaQueryWrapper);
        comment.setRemarkNum(comment.getRemarkNum()-1);
        commentService.updateById(comment);
        return Result.success("删除成功!");
    }

    @GetMapping
    public Result<Page<RemarkVo>> pageRemarks(@RequestParam Long id, @RequestParam Integer page, @RequestParam Integer size) {
        Page<Remark> remarkPage = new Page<>(page, size);
        LambdaQueryWrapper<Remark> remarkLambdaQueryWrapper = new LambdaQueryWrapper<>();
        remarkLambdaQueryWrapper.eq(Remark::getCommentId, id);
        Page<Remark> result = remarkService.page(remarkPage, remarkLambdaQueryWrapper);
        return Result.success(userLikeRemarkService.isLike(result));
    }

    @GetMapping("/all")
    public Result<List<RemarkVo>> getAll(@RequestParam Long id) {
        ArrayList<RemarkVo> remarkVos = new ArrayList<>();
        LambdaQueryWrapper<Remark> remarkLambdaQueryWrapper = new LambdaQueryWrapper<>();
        remarkLambdaQueryWrapper.eq(Remark::getCommentId ,id);
        remarkService.list(remarkLambdaQueryWrapper).forEach(remark -> {
            RemarkVo remarkVo = new RemarkVo();
            BeanUtils.copyProperties(remark, remarkVo);
            LambdaQueryWrapper<UserLikeRemark> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(UserLikeRemark::getUserId, BaseContext.getCurrentId());
            lambdaQueryWrapper.eq(UserLikeRemark::getRemarkId, remark.getId());
            List<UserLikeRemark> list = userLikeRemarkService.list(lambdaQueryWrapper);
            remarkVo.setLike(list.size() != 0);
            remarkVos.add(remarkVo);
        });
        return Result.success(remarkVos);
    }

}
