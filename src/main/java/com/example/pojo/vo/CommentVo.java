package com.example.pojo.vo;

import com.example.pojo.entity.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommentVo extends Comment {
    private boolean isLike;
    private String timeExplain;
    private int likes;
}
