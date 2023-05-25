package org.zzd.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.zzd.pojo.SecuritySystemUser;

/**
 * @apiNote 获取当前登录的用户
 * @author zzd
 * @date 2023/5/24 14:46
 */
public class SecurityUtils {
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
