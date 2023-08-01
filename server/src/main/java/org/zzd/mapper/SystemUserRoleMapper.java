package org.zzd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.zzd.entity.SystemUserRole;

import java.util.List;

/**
 * @apiNote 用户角色(SystemUserRole)数据库访问层
 * @author zzd
 * @date 2023-06-05 20:15:01
 */
@Repository
public interface SystemUserRoleMapper extends BaseMapper<SystemUserRole> {
    @Select("select user_id from t_system_user_role where role_id = #{roleId}")
    List<String> getUserIds(String roleId);

    /**
     * @apiNote 添加用户角色
     * @param userId 用户id
     * @param roleIds 角色id
     */
    void insertUserRole(@Param("userId") Long userId, @Param("roleIds") List<Long> roleIds);
}

