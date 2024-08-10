package com.example.service.impl;

import com.example.pojo.entity.Comment;
import com.example.mapper.CommentMapper;
import com.example.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 一级评论 服务实现类
 * </p>
 *
 * @author nask137
 * @since 2024-08-01
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
