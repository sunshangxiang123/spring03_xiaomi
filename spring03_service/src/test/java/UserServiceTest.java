import com.qf.pojo.User;
import com.qf.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UserServiceTest {
    @Test
    public void testRegister(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        userService.register(new User(null, "小狗", "123456", "13232323322", "男", 1, 0, "sadasd"));
    }
    @Test
    public void testCheckUserName(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        User user = userService.checkUserName("冯军伟");
        System.out.println(user);
    }
    @Test
    public void testLogin(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        User user = userService.login("小狗", "123456");
        System.out.println(user);
    }
    @Test
    public void testFindAll(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        List<User> userList = userService.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }
    @Test
    public void testUpdateFlag(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        userService.updateFlag(8);
    }
}
