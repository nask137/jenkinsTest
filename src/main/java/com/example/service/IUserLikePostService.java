package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.pojo.entity.Post;
import com.example.pojo.entity.UserLikePost;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.vo.PostVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nask137
 * @since 2024-08-03
 */
@Service
public interface IUserLikePostService extends IService<UserLikePost> {

    Page<PostVo> isLike(Page<Post> result);
}
