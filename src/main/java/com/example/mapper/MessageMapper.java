package com.example.mapper;

import com.example.pojo.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户聊天信息 Mapper 接口
 * </p>
 *
 * @author nask137
 * @since 2024-08-01
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}
