package top.dzygod.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: dingziyuan
 * @Date: 18-12-7 下午1:56
 * @Description: *
 */
public class CustomRealm extends AuthorizingRealm {

    private Map<String, String> map = new HashMap<>();

    {
        map.put("zhangsan", "123456");
        setName("customRealm");
    }

    /**
     * 权限验证
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1.从参数中拿到当前登录的用户名
        String username = (String) principals.getPrimaryPrincipal();
        //2.用户名查询
        Set<String> roles = getRolesByUsername(username);
        Set<String> permissions = getPermissionByUsername(username);
        //3.创建校验实例
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 身份验证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.从securityManager安全管理中心的认证信息里,获取当前登录用户名
        String username = (String) token.getPrincipal();

        //2.通过用户名到数据库中获取凭证
        String password = getPasswordByName(username);

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("zhangsan"
                , password, "customRealm");

        return authenticationInfo;
    }

    private String getPasswordByName(String username) {
        return map.get(username);
    }

    private Set<String> getPermissionByUsername(String username) {
        Set<String> permissions = new HashSet<>();
        permissions.add("user:delete");
        permissions.add("user:select");
        return permissions;
    }

    private Set<String> getRolesByUsername(String username) {
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        roles.add("student");
        return roles;
    }

}
