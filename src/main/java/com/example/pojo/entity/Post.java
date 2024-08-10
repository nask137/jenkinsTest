package com.example.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 帖子
 * </p>
 *
 * @author nask137
 * @since 2024-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("post")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 图片url
     */
    @TableField("imgUrl")
    private String imgUrl;

    /**
     * 对应的非遗
     */
    @TableField("culture_id")
    private Long cultureId;

    @TableField("content")
    private String content;

    @TableField("user_id")
    private Long userId;
}
