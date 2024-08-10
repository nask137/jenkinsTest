package com.example.mapper;

import com.example.pojo.entity.UserLikeComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 哟ing胡点赞了那些评论 Mapper 接口
 * </p>
 *
 * @author nask137
 * @since 2024-08-02
 */
@Mapper
public interface UserLikeCommentMapper extends BaseMapper<UserLikeComment> {

}
