package com.example.service;

import com.example.pojo.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 一级评论 服务类
 * </p>
 *
 * @author nask137
 * @since 2024-08-01
 */
@Service
public interface ICommentService extends IService<Comment> {

}
