package com.example.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.context.BaseContext;
import com.example.pojo.Result;
import com.example.pojo.entity.Comment;
import com.example.pojo.entity.Post;
import com.example.pojo.entity.Remark;
import com.example.pojo.entity.UserLikePost;
import com.example.pojo.vo.PostVo;
import com.example.service.ICommentService;
import com.example.service.IPostService;
import com.example.service.IRemarkService;
import com.example.service.IUserLikePostService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 帖子 前端控制器
 * </p>
 *
 * @author nask137
 * @since 2024-08-02
 */
@RestController
@Data
@RequestMapping("/posts")
public class PostController {
    private final IPostService postService;
    private final IUserLikePostService userLikePostService;
    private  final ICommentService commentService;
    private final IRemarkService remarkService;
    @PostMapping
    public Result<String> save(@RequestBody Post post) {
        post.setUserId(BaseContext.getCurrentId());
        postService.save(post);
        return Result.success("添加成功!");
    }
    @DeleteMapping
    @Transactional
    public Result<String> delete(@RequestParam Long id) {
        Post one = postService.getById(id);
        if (!Objects.equals(one.getUserId(), BaseContext.getCurrentId())) return Result.error("无权限!");
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        commentLambdaQueryWrapper.eq(Comment::getPostId,id);
        for (Comment comment : commentService.list(commentLambdaQueryWrapper)) {
            LambdaQueryWrapper<Remark> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Remark::getCommentId,comment.getId());
            remarkService.remove(lambdaQueryWrapper);
            commentService.removeById(comment.getId());
        }
        postService.removeById(id);
        return Result.success("删除成功!");
    }
    @GetMapping("/{id}")
    public Result<PostVo> getById(@PathVariable Long id) {
        Post result = postService.getById(id);
        PostVo postVo = new PostVo();
        BeanUtils.copyProperties(result, postVo);
        LambdaQueryWrapper<UserLikePost> userLikePostLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLikePostLambdaQueryWrapper.eq(UserLikePost::getPostId, result.getId());
        userLikePostLambdaQueryWrapper.eq(UserLikePost::getUserId, BaseContext.getCurrentId());
        List<UserLikePost> userLikePosts = userLikePostService.list(userLikePostLambdaQueryWrapper);
        postVo.setLike(userLikePosts.size() != 0);
        LambdaQueryWrapper<UserLikePost> userLikePostLambdaQueryWrapper2 = new LambdaQueryWrapper<>();
        userLikePostLambdaQueryWrapper2.eq(UserLikePost::getPostId, result.getId());
        List<UserLikePost> allLikePosts = userLikePostService.list(userLikePostLambdaQueryWrapper2);
        postVo.setLikes(allLikePosts.size());
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        int num=0;
        for (Comment comment : commentService.list(commentLambdaQueryWrapper)) {
            num=num+comment.getRemarkNum();
        }
        postVo.setCommentsNum(num);
        return Result.success(postVo);
    }
    @GetMapping
    public Result<Page<PostVo>> pagePost(@RequestParam Integer page, @RequestParam Integer size) {
        Page<Post> postPage = new Page<>(page, size);
        Page<Post> result = postService.page(postPage);
        return Result.success( userLikePostService.isLike(result));
    }
}
