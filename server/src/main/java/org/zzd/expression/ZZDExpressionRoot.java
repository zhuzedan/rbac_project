package org.zzd.expression;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.zzd.utils.SecurityUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @apiNote 自定义权限校验配置
 * @author zzd
 * @date 2023/5/26 16:03
 */
@Component("ex")
public class ZZDExpressionRoot {
    public boolean hasAuthority(String ...permissions){
        //获取当前用户的权限
        List<String> exPermissions = SecurityUtils.getCurrentSecuritySystemUser().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        //判断用户权限集合中是否存在authority
        return Arrays.stream(permissions).anyMatch(exPermissions::contains);
    }
}
