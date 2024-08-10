package com.example.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 一级评论
 * </p>
 *
 * @author nask137
 * @since 2024-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 帖子id
     */
    @TableField("post_id")
    private Long postId;

    /**
     * 发表时间
     */
    @TableField("time")
    private LocalDateTime time;

    @TableField("remark_num")
    private Integer remarkNum;

    /**
     * 评论内容
     */
    @TableField("content")
    private String content;

    /**
     * 评论图片Url
     */
    @TableField("img")
    private String img;


}
