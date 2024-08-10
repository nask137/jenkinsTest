package com.example.service.impl;

import com.example.pojo.entity.Post;
import com.example.mapper.PsotMapper;
import com.example.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 帖子 服务实现类
 * </p>
 *
 * @author nask137
 * @since 2024-08-01
 */
@Service
public class PostServiceImpl extends ServiceImpl<PsotMapper, Post> implements IPostService {

}
