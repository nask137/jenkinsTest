package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.context.BaseContext;
import com.example.mapper.CommentMapper;
import com.example.pojo.entity.Comment;
import com.example.pojo.entity.Post;
import com.example.pojo.entity.UserLikePost;
import com.example.mapper.UserLikePostMapper;
import com.example.pojo.entity.UserLikeRemark;
import com.example.pojo.vo.PostVo;
import com.example.service.IUserLikePostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nask137
 * @since 2024-08-03
 */
@EqualsAndHashCode(callSuper = true)
@Service
@Data
public class UserLikePostServiceImpl extends ServiceImpl<UserLikePostMapper, UserLikePost> implements IUserLikePostService {

    private  final  UserLikePostMapper userLikePostMapper;
    private final CommentMapper commentMapper;
    @Override
    public Page<PostVo> isLike(Page<Post> postPage) {
        ArrayList<PostVo> postVos = new ArrayList<>();
        postPage.getRecords().forEach(post -> {
            PostVo postVo = new PostVo();
            BeanUtils.copyProperties(post, postVo);
            LambdaQueryWrapper<UserLikePost> userLikePostLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLikePostLambdaQueryWrapper.eq(UserLikePost::getPostId, post.getId());
            userLikePostLambdaQueryWrapper.eq(UserLikePost::getUserId, BaseContext.getCurrentId());
            List<UserLikePost> userLikePosts = userLikePostMapper.selectList(userLikePostLambdaQueryWrapper);
            postVo.setLike(userLikePosts.size() != 0);
            LambdaQueryWrapper<UserLikePost> userLikePostLambdaQueryWrapper2 = new LambdaQueryWrapper<>();
            userLikePostLambdaQueryWrapper2.eq(UserLikePost::getPostId, post.getId());
            List<UserLikePost> allLikePosts = userLikePostMapper.selectList(userLikePostLambdaQueryWrapper2);
            postVo.setLikes(allLikePosts.size());
            LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
            commentLambdaQueryWrapper.eq(Comment::getPostId,post.getId());
            int num=0;
            for (Comment comment : commentMapper.selectList(commentLambdaQueryWrapper)) {
                num=num+comment.getRemarkNum();
            }
            postVo.setCommentsNum(num);
            postVos.add(postVo);
        });
        Page<PostVo> postVoPage = new Page<>();
        postVoPage.setPages(postPage.getPages());
        postVoPage.setCurrent(postPage.getCurrent());
        postVoPage.setTotal(postPage.getTotal());
        postVoPage.setSize(postPage.getSize());
        postVoPage.setRecords(postVos);
        return postVoPage;
    }
}
