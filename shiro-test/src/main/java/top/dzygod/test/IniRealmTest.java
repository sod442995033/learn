package top.dzygod.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @Author: dingziyuan
 * @Date: 18-12-7 上午9:30
 * @Description: *
 */
public class IniRealmTest {


    @Test
    public void test() {
        IniRealm iniRealm = new IniRealm("classpath:reaml.ini");

        //获取securityManager环境
        DefaultSecurityManager manager = new DefaultSecurityManager();
        manager.setRealm(iniRealm);

        //主体提交认证请求
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");
        subject.login(token);
        System.out.println(subject.isAuthenticated());

        subject.checkRole("admin");
        subject.checkPermission("user:delete");
    }
}

