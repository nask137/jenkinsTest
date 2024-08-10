package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.context.BaseContext;
import com.example.pojo.entity.Remark;

import com.example.pojo.entity.UserLikeRemark;
import com.example.mapper.UserLikeRemarkMapper;
import com.example.pojo.vo.RemarkVo;
import com.example.service.IUserLikeRemarkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.utils.TimeExplainUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户点赞二级评论表 服务实现类
 * </p>
 *
 * @author nask137
 * @since 2024-08-02
 */
@EqualsAndHashCode(callSuper = true)
@Service
@Data
public class UserLikeRemarkServiceImpl extends ServiceImpl<UserLikeRemarkMapper, UserLikeRemark> implements IUserLikeRemarkService {
    private final UserLikeRemarkMapper userLikeRemarkMapper;
    @Override
    public Page<RemarkVo> isLike(Page<Remark> remarkPage) {
        ArrayList<RemarkVo> remarkVos = new ArrayList<>();
        remarkPage.getRecords().forEach(remark -> {
            RemarkVo remarkVo = new RemarkVo();
            BeanUtils.copyProperties(remark, remarkVo);
            LambdaQueryWrapper<UserLikeRemark> userLikeRemarkLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLikeRemarkLambdaQueryWrapper.eq(UserLikeRemark::getRemarkId, remark.getId());
            userLikeRemarkLambdaQueryWrapper.eq(UserLikeRemark::getUserId, BaseContext.getCurrentId());
            List<UserLikeRemark> userLikeRemarks = userLikeRemarkMapper.selectList(userLikeRemarkLambdaQueryWrapper);
            remarkVo.setLike(userLikeRemarks.size() != 0);
            LambdaQueryWrapper<UserLikeRemark> userLikeRemarkLambdaQueryWrapper2 = new LambdaQueryWrapper<>();
            userLikeRemarkLambdaQueryWrapper2.eq(UserLikeRemark::getRemarkId, remark.getId());
            List<UserLikeRemark> allLikeRemarks = userLikeRemarkMapper.selectList(userLikeRemarkLambdaQueryWrapper2);
            remarkVo.setLikes(allLikeRemarks.size());
            remarkVo.setTimeExplain(TimeExplainUtils.timeExplain(remarkVo.getTime()));
            remarkVos.add(remarkVo);
        });
        Page<RemarkVo> remarkVoPage = new Page<>();
        remarkVoPage.setPages(remarkPage.getPages());
        remarkVoPage.setCurrent(remarkPage.getCurrent());
        remarkVoPage.setTotal(remarkPage.getTotal());
        remarkVoPage.setSize(remarkPage.getSize());
        remarkVoPage.setRecords(remarkVos);
        return remarkVoPage;
    }
}
