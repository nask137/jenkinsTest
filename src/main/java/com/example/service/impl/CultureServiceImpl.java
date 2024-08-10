package com.example.service.impl;

import com.example.pojo.entity.Culture;
import com.example.mapper.CultureMapper;
import com.example.service.ICultureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 非遗文化 服务实现类
 * </p>
 *
 * @author nask137
 * @since 2024-08-01
 */
@Service
public class CultureServiceImpl extends ServiceImpl<CultureMapper, Culture> implements ICultureService {

}
