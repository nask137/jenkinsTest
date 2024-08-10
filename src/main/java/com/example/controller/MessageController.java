package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.pojo.Result;
import com.example.pojo.entity.Message;
import com.example.service.IMessageService;
import lombok.Data;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户聊天信息 前端控制器
 * </p>
 *
 * @author nask137
 * @since 2024-08-02
 */
@RestController
@Data
@RequestMapping("/messages")
public class MessageController {
    private final IMessageService messageService;

    private final OpenAiChatModel openAiChatModel;

    @Value(value = "${Static.MaxMessageNum}")
    private int MaxMessageNum;

    @GetMapping
    public  Result<List<Message>> getMessages(@RequestParam Long id){
        LambdaQueryWrapper<Message> messageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        messageLambdaQueryWrapper.eq(Message::getChatId,id);
        return Result.success(messageService.list(messageLambdaQueryWrapper));
    }
    @PostMapping
    public Result<Message> sentMessage(@RequestParam String message, @RequestParam Long chatId) {
        Message message2 = new Message();
        message2.setChatId(chatId);
        message2.setMessage(message);
        message2.setRole(0);
        message2.setTime(LocalDateTime.now());
        messageService.save(message2);
        LambdaQueryWrapper<Message> messageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        messageLambdaQueryWrapper.eq(Message::getChatId, chatId);
        List<Message> list = messageService.list(messageLambdaQueryWrapper);
        List<Message> result;
        if (list.size() > MaxMessageNum) {
            int start = list.size() - MaxMessageNum;
            result = list.subList(start, list.size());
        } else {
            result = new ArrayList<>(list);
        }
        //拿到后MaxMessageNum个聊天,组装成openAi的Message
        ArrayList<org.springframework.ai.chat.messages.Message> messages = new ArrayList<>();
        result.forEach(message1 -> {
            if (message1.getRole() == 0) {//用户
                UserMessage userMessage = new UserMessage(message1.getMessage());
                messages.add(userMessage);
            } else {//助手
                AssistantMessage assistantMessage = new AssistantMessage(message1.getMessage());
                messages.add(assistantMessage);
            }
        });
        //发送请求,获取结果
        ChatResponse call = openAiChatModel.call( new Prompt(messages));
        Message message1 = new Message();
        message1.setMessage(call.getResult().getOutput().getContent());
        message1.setTime(LocalDateTime.now());
        message1.setRole(1);
        message1.setChatId(chatId);
        messageService.save(message1);
        return Result.success(message1);
    }
}
