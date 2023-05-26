package org.zzd.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.zzd.entity.SystemUser;
import org.zzd.exception.ResponseException;
import org.zzd.pojo.SecuritySystemUser;

/**
 * @apiNote 获取当前登录的用户
 * @author zzd
 * @date 2023/5/24 14:46
 */
public class SecurityUtils {
    /**
     * @apiNote 获得当前的带detail的用户
     * @return org.zzd.pojo.SecuritySystemUser
     */
    public static SecuritySystemUser getCurrentSecuritySystemUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new ResponseException("用户信息查询失败");
        }
        return (SecuritySystemUser) authentication.getPrincipal();
    }

    /**
     * @apiNote 获得当前用户
     * @return org.zzd.entity.SystemUser
     */
    public static SystemUser getCurrentSystemUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new ResponseException("用户信息查询失败");
        }
        if (authentication.getPrincipal() instanceof UserDetails) {
            SecuritySystemUser systemUser = (SecuritySystemUser) authentication.getPrincipal();
            return systemUser.getSystemUser();
        }
        throw new ResponseException("找不到当前登录的信息");
    }

    /**
     * @apiNote 获取当前用户id
     * @return java.lang.Long
     */
    public static Long getUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            SecuritySystemUser user = (SecuritySystemUser) authentication.getPrincipal();
            return user.getSystemUser().getId();
        }
        return null;
    }
}
