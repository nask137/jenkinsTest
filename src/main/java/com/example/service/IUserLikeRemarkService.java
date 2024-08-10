package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.pojo.entity.Remark;
import com.example.pojo.entity.UserLikeRemark;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.vo.RemarkVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户点赞二级评论表 服务类
 * </p>
 *
 * @author nask137
 * @since 2024-08-02
 */
@Service
public interface IUserLikeRemarkService extends IService<UserLikeRemark> {

    Page<RemarkVo> isLike(Page<Remark> result);
}
