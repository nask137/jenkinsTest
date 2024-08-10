package com.example.mapper;

import com.example.pojo.entity.UserLikeRemark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户点赞二级评论表 Mapper 接口
 * </p>
 *
 * @author nask137
 * @since 2024-08-02
 */
@Mapper
public interface UserLikeRemarkMapper extends BaseMapper<UserLikeRemark> {

}
