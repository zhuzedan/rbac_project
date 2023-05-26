package org.zzd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zzd.entity.SystemRole;
import org.zzd.param.BasePageParam;
import org.zzd.result.ResponseResult;
import org.zzd.utils.PageHelper;

import java.util.HashMap;

/**
 * @apiNote 角色(SystemRole)服务接口
 * @author zzd
 * @date 2023-05-26 14:22:55
 */
public interface SystemRoleService extends IService<SystemRole> {
    ResponseResult<PageHelper<SystemRole>> queryPage(BasePageParam params);
}

