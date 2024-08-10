package com.example.service;

import com.example.pojo.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户聊天信息 服务类
 * </p>
 *
 * @author nask137
 * @since 2024-08-01
 */
@Service
public interface IMessageService extends IService<Message> {

}
