package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.context.BaseContext;
import com.example.pojo.entity.Comment;
import com.example.pojo.entity.UserLikeComment;
import com.example.mapper.UserLikeCommentMapper;
import com.example.pojo.vo.CommentVo;
import com.example.service.IUserLikeCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.utils.TimeExplainUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 哟ing胡点赞了那些评论 服务实现类
 * </p>
 *
 * @author nask137
 * @since 2024-08-02
 */
@EqualsAndHashCode(callSuper = true)
@Service
@Data
public class UserLikeCommentServiceImpl extends ServiceImpl<UserLikeCommentMapper, UserLikeComment> implements IUserLikeCommentService {
    private final UserLikeCommentMapper userLikeCommentMapper;

    @Override
    public Page<CommentVo> isLike(Page<Comment> commentPage) {

        ArrayList<CommentVo> commentVos = new ArrayList<>();
        commentPage.getRecords().forEach(comment -> {
            CommentVo commentVo = new CommentVo();
            BeanUtils.copyProperties(comment, commentVo);
            LambdaQueryWrapper<UserLikeComment> userLikeCommentLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLikeCommentLambdaQueryWrapper.eq(UserLikeComment::getCommentId, comment.getId());
            userLikeCommentLambdaQueryWrapper.eq(UserLikeComment::getUserId, BaseContext.getCurrentId());
            List<UserLikeComment> userLikeComments = userLikeCommentMapper.selectList(userLikeCommentLambdaQueryWrapper);
            commentVo.setLike(userLikeComments.size() != 0);
            LambdaQueryWrapper<UserLikeComment> userLikeCommentLambdaQueryWrapper2 = new LambdaQueryWrapper<>();
            userLikeCommentLambdaQueryWrapper2.eq(UserLikeComment::getCommentId, comment.getId());
            List<UserLikeComment> allLikeComments = userLikeCommentMapper.selectList(userLikeCommentLambdaQueryWrapper2);
            commentVo.setLikes(allLikeComments.size());
            commentVo.setTimeExplain(TimeExplainUtils.timeExplain(commentVo.getTime()));
            commentVos.add(commentVo);
        });
        Page<CommentVo> commentVoPage = new Page<>();
        commentVoPage.setPages(commentPage.getPages());
        commentVoPage.setCurrent(commentPage.getCurrent());
        commentVoPage.setTotal(commentPage.getTotal());
        commentVoPage.setSize(commentPage.getSize());
        commentVoPage.setRecords(commentVos);
        return commentVoPage;
    }
}
