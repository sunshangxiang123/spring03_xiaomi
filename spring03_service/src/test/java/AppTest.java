import com.qf.pojo.User;
import com.qf.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest {
    @Test
    public void testUserService(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        User user = userService.login("冯军伟", "e10adc3949ba59abbe56e057f20f883e");
        System.out.println(user);
    }
}

