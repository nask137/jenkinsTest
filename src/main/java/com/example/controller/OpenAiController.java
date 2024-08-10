package com.example.controller;

import com.example.pojo.Result;
import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@RestController

@RequestMapping("/ai")
public class OpenAiController {

    private final OpenAiChatModel openAiChatModel;
    private final OpenAiImageModel openAiImageModel;


    public OpenAiController(OpenAiChatModel openAiChatModel, OpenAiImageModel openAiImageModel) {
        this.openAiChatModel = openAiChatModel;
        this.openAiImageModel = openAiImageModel;
    }

    @GetMapping("/chat")
    public Result<String> caht(@RequestParam(value = "message", defaultValue = "今天成都的天气怎么样?") String message) {
        openAiChatModel.call(new Prompt(message));
        return Result.success(openAiChatModel.call(message));

    }

    @GetMapping("/chatWithImage")
    /*
      @apiNote 此接口为图像处理接口(同时输入文字和图片数据),由于当前所购买模型不支持,暂不可用
     * 此接口内的方法待更新,等待open AI 依赖 的稳定发行版
     * @param imgUrl 图片路径 必须为.png后缀,其他的暂不支持
     */
    public Result<String> chatWithImage(@RequestParam(value = "imgUrl") String imgUrl,
                                              @RequestParam(value = "message", defaultValue = "请分析一下这张图片") String message) {
        try {
            URL url = new URL(imgUrl);
            MediaType mediaType=null;
            //判断媒体格式
            if(imgUrl.contains(".png"))mediaType= MediaType.parseMediaType("image/png");
            if(imgUrl.contains(".jpg"))mediaType= MediaType.parseMediaType("image/jpg");
            Media media = new Media(mediaType, url);
            // 创建媒体列表
            List<Media> mediaList = List.of(media);
            // 创建用户消息
            UserMessage userMessage = new UserMessage(message, mediaList);
            // 调用聊天模型
            ChatResponse response = openAiChatModel.call(new Prompt(
                    List.of(userMessage),
                    OpenAiChatOptions.builder()
                            .withModel(OpenAiApi.ChatModel.GPT_4_O.getValue())
                            .build()
            ));
            // 返回结果
            return Result.success(response.getResult().getOutput().getContent());
        } catch (IOException e) {
            // 处理可能的 IO 异常
            e.printStackTrace();
            return Result.error("Failed to process image URL.");
        }
    }

    @GetMapping("/image")
    /*
      @apiNote 此接口为图片生成接口根据描述生成对应的图片
     */
    public Result<String> image(@RequestParam(value = "message") String message) {
        ImagePrompt imagePrompt = new ImagePrompt(message);
        ImageResponse call = openAiImageModel.call(imagePrompt);
        return Result.success(call.getResult().getOutput().getUrl());
    }
}
