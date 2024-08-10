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
 * 用户聊天信息
 * </p>
 *
 * @author nask137
 * @since 2024-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("message")
    private String message;

    /**
     * 身份{0,1}(0:用户;1:助手)
     */
    @TableField("role")
    private Integer role;

    /**
     * 时间
     */
    @TableField("time")
    private LocalDateTime time;

    @TableField("chat_id")
    private Long chatId;
}
