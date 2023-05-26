package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzd.entity.SystemRole;
import org.zzd.mapper.SystemRoleMapper;
import org.zzd.param.BasePageParam;
import org.zzd.result.ResponseResult;
import org.zzd.service.SystemRoleService;
import org.zzd.utils.PageHelper;

/**
 * @apiNote 角色(SystemRole)服务实现类
 * @author zzd
 * @date 2023-05-26 14:22:55
 */
@Service("systemRoleService")
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements SystemRoleService {
    @Autowired
    private SystemRoleMapper systemRoleMapper;

    @Override
    public ResponseResult<PageHelper<SystemRole>> queryPage(BasePageParam params) {
        LambdaQueryWrapper<SystemRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        Page<SystemRole> page = new Page<>(params.getPageNum(), params.getPageSize());
        IPage<SystemRole> iPage = systemRoleMapper.selectPage(page, lambdaQueryWrapper);
        return ResponseResult.success(PageHelper.restPage(iPage));
    }
}

