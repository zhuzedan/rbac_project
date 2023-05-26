package org.zzd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.zzd.entity.SystemUser;

/**
 * 用户表(SystemUser)表数据库访问层
 *
 * @author zzd
 * @since 2023-03-02 13:53:39
 */
@Repository
public interface SystemUserMapper extends BaseMapper<SystemUser> {

}

