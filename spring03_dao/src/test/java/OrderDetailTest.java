import com.qf.dao.OrderDetailMapper;
import com.qf.pojo.OrderDetail;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

public class OrderDetailTest {
    @Test
    public void testAdd(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderDetailMapper orderDetailMapper = context.getBean("orderDetailMapper", OrderDetailMapper.class);
        orderDetailMapper.add(new OrderDetail(2, "123456", 6, 5, new BigDecimal(542.3), null));
    }
}
