import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;

public class JdbcRealm {
    public static void main(String[] args) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:JdbcRealm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("gtp", "123");
        try {
            currentUser.login(token);
            System.out.println("身份验证成功");
        }catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("身份验证失败");
        }
        currentUser.logout();
    }
}
