package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.context.BaseContext;
import com.example.pojo.Result;
import com.example.pojo.entity.Chat;
import com.example.pojo.entity.Message;
import com.example.service.IChatService;
import com.example.service.IMessageService;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * <p>
 * 聊天信息表 前端控制器
 * </p>
 *
 * @author nask137
 * @since 2024-08-02
 */
@RestController
@Data
@RequestMapping("/chats")
public class ChatController {
    private final IChatService chatService;
private final IMessageService messageService;
    @PostMapping
    public Result<String> creatChat() {
        Chat chat = new Chat();
        chat.setUserId(BaseContext.getCurrentId());
        chatService.save(chat);
        return Result.success("创建成功!");
    }
    @PutMapping
    public Result<String> updateTitle(@RequestBody Chat chat) {
        if (!Objects.equals(chat.getUserId(), BaseContext.getCurrentId())) return Result.error("权限不足!");
        chatService.updateById(chat);
        return Result.success("修改成功!");
    }
    @DeleteMapping
    @Transactional
    public Result<String> delete(@RequestParam Long id) {
        Chat one = chatService.getById(id);
        if (!Objects.equals(one.getUserId(), BaseContext.getCurrentId())) return Result.error("权限不足!");
        LambdaQueryWrapper<Message> messageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        messageLambdaQueryWrapper.eq(Message::getChatId,id);
        messageService.remove(messageLambdaQueryWrapper);
        chatService.removeById(id);
        return Result.success("删除成功!");
    }
}
