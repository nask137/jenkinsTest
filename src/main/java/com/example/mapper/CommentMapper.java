package com.example.mapper;

import com.example.pojo.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 一级评论 Mapper 接口
 * </p>
 *
 * @author nask137
 * @since 2024-08-01
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
