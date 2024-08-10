package com.example.mapper;

import com.example.pojo.entity.Chat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 聊天信息表 Mapper 接口
 * </p>
 *
 * @author nask137
 * @since 2024-08-01
 */
@Mapper
public interface ChatMapper extends BaseMapper<Chat> {

}
