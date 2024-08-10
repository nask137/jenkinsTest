package com.example.service.impl;

import com.example.pojo.entity.Message;
import com.example.mapper.MessageMapper;
import com.example.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户聊天信息 服务实现类
 * </p>
 *
 * @author nask137
 * @since 2024-08-01
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
