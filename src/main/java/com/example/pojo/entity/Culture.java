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
 * 非遗文化
 * </p>
 *
 * @author nask137
 * @since 2024-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("culture")
public class Culture implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 非遗名称
     */
    @TableField("name")
    private String name;

    /**
     * 传承人
     */
    @TableField("Inheritor")
    private String Inheritor;

    /**
     * 产生
     */
    @TableField("origin")
    private String origin;

    /**
     * 发展
     */
    @TableField("develop")
    private String develop;

    /**
     * 传承
     */
    @TableField("Legacy")
    private String Legacy;

    /**
     * 意义
     */
    @TableField("significance")
    private String significance;

    /**
     * 视频url
     */
    @TableField("video")
    private String video;

    /**
     * 点赞
     */
    @TableField("likes")
    private Integer likes;

    @TableField("user_id")
    private Long userId;

}
