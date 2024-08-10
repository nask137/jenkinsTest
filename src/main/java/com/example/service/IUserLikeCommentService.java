package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.pojo.entity.Comment;
import com.example.pojo.entity.UserLikeComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.vo.CommentVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 哟ing胡点赞了那些评论 服务类
 * </p>
 *
 * @author nask137
 * @since 2024-08-02
 */
@Service
public interface IUserLikeCommentService extends IService<UserLikeComment> {

    Page<CommentVo> isLike(Page<Comment> commentPage);

}
