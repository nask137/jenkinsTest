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
 * 耳机评论
 * </p>
 *
 * @author nask137
 * @since 2024-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("remark")
public class Remark implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 评论内容
     */
    @TableField("content")
    private String content;

    /**
     * 图片Url
     */
    @TableField("img")
    private String img;

    /**
     * 父评论id
     */
    @TableField("comment_id")
    private Long commentId;


    /**
     * 评论时间
     */
    @TableField("time")
    private LocalDateTime time;


    @TableField("user_id")
    private Long userId;

}
