package top.dzygod.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @Author: dingziyuan
 * @Date: 18-12-7 上午11:22
 * @Description: *
 */
public class JdbcRealmTest {

    DruidDataSource dataSource = new DruidDataSource();

    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/springboot_demo");
        dataSource.setUsername("dzygod");
        dataSource.setPassword("dzy998877");
    }


    @Test
    public void test() {

        JdbcRealm realm = new JdbcRealm();
        realm.setDataSource(dataSource);
        realm.setAuthenticationQuery("select password from tb_user where user_name = ?");
        //获取管理实例
        DefaultSecurityManager manager = new DefaultSecurityManager();
        manager.setRealm(realm);
        //验证
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");
        subject.login(token);
        System.out.println(subject.isAuthenticated());

    }
}
