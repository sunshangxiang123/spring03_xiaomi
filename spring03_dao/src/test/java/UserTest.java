import com.qf.dao.UserMapper;
import com.qf.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UserTest {
    @Test
    public void testFindAll(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }
    @Test
    public void testFindById(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        User user = userMapper.findById(7);
        System.out.println(user);
    }
    @Test
    public void testFindByUserNameAndPassword(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        User user = userMapper.findByUserNameAndPassword("冯军伟", "e10adc3949ba59abbe56e057f20f883e");
        System.out.println(user);

    }
    @Test
    public void testCheckUserName(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        User user = userMapper.checkUserName("冯军伟");
        System.out.println(user);

    }
    @Test
    public void testAdd(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        userMapper.add(new User(null, "能天使", "123456", "1196437683@qq.com", "男", 1, 0, "asdasdasdasdas"));

    }
    @Test
    public void testUpdate(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        userMapper.update(new User(8, "能天使", "123456", "1196437683@qq.com", "女", 1, 0, "asdasdasdasdas"));

    }
    @Test
    public void testDelete(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        userMapper.delete(9);
    }
    @Test
    public void testFindCount(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        long count = userMapper.findCount("gender='男'");
        System.out.println(count);

    }
    @Test
    public void testUpdateFlag(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        userMapper.updateFlag(8);
    }

}
