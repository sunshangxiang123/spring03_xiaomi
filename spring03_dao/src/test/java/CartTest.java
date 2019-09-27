import com.qf.dao.CartMapper;
import com.qf.pojo.Cart;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class CartTest {
    @Test
    public void testFindByIdAndGid(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CartMapper cartMapper = context.getBean("cartMapper", CartMapper.class);
        Cart cart = cartMapper.findByIdAndGid(8, 5);
        System.out.println(cart);
    }
    @Test
    public void testAdd(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CartMapper cartMapper = context.getBean("cartMapper", CartMapper.class);
        cartMapper.add(new Cart(8, 7, 4, new BigDecimal(20.3), null));
    }
    @Test
    public void testUpdate(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CartMapper cartMapper = context.getBean("cartMapper", CartMapper.class);
        cartMapper.update(new Cart(8, 7, 5, new BigDecimal(503.2), null));
    }
    @Test
    public void testFindById(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CartMapper cartMapper = context.getBean("cartMapper", CartMapper.class);
        List<Cart> cartList = cartMapper.findById(8);
        for (Cart cart : cartList) {
            System.out.println(cart);
        }
    }
    @Test
    public void testDeleteByUid(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CartMapper cartMapper = context.getBean("cartMapper", CartMapper.class);
        cartMapper.deleteByUid(6);
    }
    @Test
    public void testDelete(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CartMapper cartMapper = context.getBean("cartMapper", CartMapper.class);
        cartMapper.delete(8, 7);
    }
}
