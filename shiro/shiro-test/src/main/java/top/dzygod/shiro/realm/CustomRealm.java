package top.dzygod.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        return null;
    }

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
}
