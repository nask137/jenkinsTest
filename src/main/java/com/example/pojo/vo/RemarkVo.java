package com.example.pojo.vo;

import com.example.pojo.entity.Remark;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RemarkVo extends Remark {

    private boolean isLike;
    private String timeExplain;
    private  int likes;
}
