import com.qf.dao.OrderMapper;
import com.qf.pojo.Order;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderTest {
    @Test
    public void testAdd(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderMapper orderMapper = context.getBean("orderMapper", OrderMapper.class);
        orderMapper.add(new Order("1234564", 8, new BigDecimal(456.5), "5", new Date(), 7, null,null));
    }
    @Test
    public void testFindByUid(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderMapper orderMapper = context.getBean("orderMapper", OrderMapper.class);
        List<Order> orderList = orderMapper.findByUid(8);
        for (Order order : orderList) {
            System.out.println(order);
        }

    }
    @Test
    public void testFindByOid(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderMapper orderMapper = context.getBean("orderMapper", OrderMapper.class);
        Order order = orderMapper.findByOid("123456");
        System.out.println(order);

    }
    @Test
    public void testFindDetailByOid(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderMapper orderMapper = context.getBean("orderMapper", OrderMapper.class);
        Order order = orderMapper.findByOid("123456");
        System.out.println(order);

    }
}
