package top.dzygod.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import top.dzygod.shiro.realm.CustomRealm;

/**
 * @Author: dingziyuan
 * @Date: 18-12-7 下午2:55
 * @Description: *
 */
public class CustomRealmTest {

    @Test
    public void test() {
        CustomRealm realm = new CustomRealm();
        //获取验证管理
        DefaultSecurityManager manager = new DefaultSecurityManager();
        manager.setRealm(realm);
        //校验
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");
        subject.login(token);
        subject.checkPermissions("user:delete","user:select");
        subject.checkRole("admin");
        System.out.println(subject.isAuthenticated());
    }
}
