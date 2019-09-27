import com.qf.pojo.Order;
import com.qf.service.OrderService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceTest {
    @Test
    public void testSaveOrder(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderService orderService = context.getBean("orderServiceImpl", OrderService.class);
        orderService.saveOrder(new Order("121212",8,new BigDecimal(120.0),"5",new Date(),7,null,null), new ArrayList<>());
    }
    @Test
    public void testFindByUid(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderService orderService = context.getBean("orderServiceImpl", OrderService.class);
        List<Order> orderList = orderService.findByUid(8);
        for (Order order : orderList) {
            System.out.println(order);
        }

    }
    @Test
    public void testFindByOid(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderService orderService = context.getBean("orderServiceImpl", OrderService.class);
        Order order = orderService.findByOid("123456");
        System.out.println(order);
    }
}
