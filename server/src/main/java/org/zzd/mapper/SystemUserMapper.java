package org.zzd.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.zzd.entity.SystemUser;
import org.zzd.vo.user.QueryUserPageVo;

/**
 * 用户表(SystemUser)表数据库访问层
 *
 * @author zzd
 * @since 2023-03-02 13:53:39
 */
@Repository
public interface SystemUserMapper extends BaseMapper<SystemUser> {

    IPage<QueryUserPageVo> selectUserPage(Page<SystemUser> page, @Param(Constants.WRAPPER) LambdaQueryWrapper<SystemUser> lambdaQueryWrapper);
}
