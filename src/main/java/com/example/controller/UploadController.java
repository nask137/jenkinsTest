package com.example.controller;

import com.example.pojo.Result;
import com.example.utils.AliOSSUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@Data
public class UploadController {
    private final AliOSSUtils aliOSSUtils;
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        String url = aliOSSUtils.upload(file);
        return Result.success(url);
    }
}
