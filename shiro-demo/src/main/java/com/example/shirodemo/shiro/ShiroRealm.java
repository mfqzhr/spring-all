package com.example.shirodemo.shiro;

import com.example.shirodemo.bean.Permission;
import com.example.shirodemo.bean.Role;
import com.example.shirodemo.bean.User;
import com.example.shirodemo.service.PermissionService;
import com.example.shirodemo.service.RoleService;
import com.example.shirodemo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 穆繁强
 * @date 2020/1/2
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    /**
     * 获取用户角色和权限
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String userName = user.getUserName();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //获取用户的角色
        List<Role> roleList = roleService.findByUserName(userName);
        Set<String> roleSet = new HashSet<>();
        roleList.forEach(role -> roleSet.add(role.getName()));
        simpleAuthorizationInfo.setRoles(roleSet);
        //获取用户的权限
        List<Permission> permissionList = permissionService.findByUserName(userName);
        System.out.println(permissionList);
        Set<String> permissionSet = new HashSet<>();
        permissionList.forEach(permission -> permissionSet.add(permission.getName()));
        //加入shiro
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

    /**
     * 登录认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户输入的用户名和密码
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        System.out.println("用户: " + userName);
        User user = userService.findByUserName(userName);
        if (user == null) {
            throw new UnknownAccountException("用户名或者密码错误");
        }
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("用户名或者密码错误");
        }
        if (user.getStatus().equals("0")) {
            throw new LockedAccountException("账号被锁定,请联系管理员");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, password, getName());
        return simpleAuthenticationInfo;
    }
}
