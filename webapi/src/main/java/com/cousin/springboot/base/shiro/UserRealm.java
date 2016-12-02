package com.cousin.springboot.base.shiro;

import com.cousin.springboot.model.pojo.SysPermission;
import com.cousin.springboot.model.pojo.SysRole;
import com.cousin.springboot.model.pojo.UserInfo;
import com.cousin.springboot.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 身份校验核心类
 *
 * @author cousin
 * @Created 2016 /12/2 11:01
 */
public class UserRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(UserRealm.class);
    @Autowired
    private UserInfoService userInfoService;


    /**
     * 此方法调用 hasRole,hasPermission 的时候才会回调
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * * 2、如果用户非正常退出，缓存自动清空；
     * * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * * （需要手动编程进行实现；放在service进行调用）
     * * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
     * * 调用clearCached方法；
     * * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("权限配置--");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo) principalCollection.getPrimaryPrincipal();



        for(SysRole sysRole : userInfo.getRoleList()){
            simpleAuthorizationInfo.addRole(sysRole.getRole());
            for (SysPermission sysPermission : sysRole.getPermissions()){
                simpleAuthorizationInfo.addStringPermission(sysPermission.getPermission());
            }
        }

        return simpleAuthorizationInfo;
    }


    /**
     * 身份校验
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("开始进行shiro的身份校验");

        String username = (String) authenticationToken.getPrincipal();
        logger.info("用户名为：{}", username);


        //这个可以做个缓存，如果不做，其实shiro也有个机制，2分钟内不会重复执行这个方法
        UserInfo userInfo = userInfoService.findByUsername(username);
        if (userInfo == null) {
            return null;
        }
/*
        * 获取权限信息:这里没有进行实现，
        * 请自行根据UserInfo,Role,Permission进行实现；
        * 获取之后可以在前端for循环显示所有链接;
        */
        //userInfo.setPermissions(userService.findPermissions(user));


        //账号判断;

        //加密方式;
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );

        //明文: 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
//      SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//           userInfo, //用户名
//           userInfo.getPassword(), //密码
//             getName()  //realm name
//      );

        return authenticationInfo;
    }
}
