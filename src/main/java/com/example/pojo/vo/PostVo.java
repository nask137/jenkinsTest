package com.example.pojo.vo;

import com.example.pojo.entity.Post;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostVo extends Post {
    private  boolean isLike;
    private  int likes;
    private int commentsNum;
}
