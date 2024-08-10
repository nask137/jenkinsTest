package com.example.service.impl;

import com.example.pojo.entity.Chat;
import com.example.mapper.ChatMapper;
import com.example.service.IChatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 聊天信息表 服务实现类
 * </p>
 *
 * @author nask137
 * @since 2024-08-01
 */
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements IChatService {

}
