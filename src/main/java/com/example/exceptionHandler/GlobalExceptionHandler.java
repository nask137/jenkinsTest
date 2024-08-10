package com.example.exceptionHandler;



import com.example.exception.CustomException;
import com.example.pojo.Result;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.ai.retry.TransientAiException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<String> sqlExceptionHandler(Exception e) {

        if (e.getMessage().contains("Duplicate entry")) {
            String[] s = e.getMessage().split(" ");
            return Result.error(s[9] + "已存在！");
        }
        return Result.error("操作失败！");
    }
    @ExceptionHandler(TransientAiException.class)
    public Result<String> aiExceptionHandler(Exception e) {


        return Result.error(e.getMessage());
    }
    @ExceptionHandler(TooManyResultsException.class)
    public Result<String> TooManyResultsException() {
        return Result.error("此评论不存在或已被删除!");
    }
    @ExceptionHandler(CustomException.class)
    public Result<String> customExceptionHandler(Exception e) {
        return Result.error(e.getMessage());
    }
}
