package top.dzygod.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author: dingziyuan
 * @Date: 2018/12/6 21:42
 * @Description: *
 */
public class AuthenticationTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser() {
        simpleAccountRealm.addAccount("张三", "123456");
    }

    @Test
    public void test() {
        //1.获取securityManager实例
        DefaultSecurityManager manager = new DefaultSecurityManager();
        manager.setRealm(simpleAccountRealm);
        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("张三", "123456");
        subject.login(token);
        System.out.println("认证:" + subject.isAuthenticated());

        subject.logout();
        System.out.println("认证:" + subject.isAuthenticated());
    }


}
