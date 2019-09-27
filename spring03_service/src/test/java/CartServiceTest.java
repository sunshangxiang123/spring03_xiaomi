import com.qf.pojo.Cart;
import com.qf.service.CartService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class CartServiceTest {
    @Test
    public void testFindByIdAndGid(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CartService cartService = context.getBean("cartServiceImpl", CartService.class);
        Cart cart = cartService.findByIdAndGid(8, 4);
        System.out.println(cart);
    }
    @Test
    public void testAdd(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CartService cartService = context.getBean("cartServiceImpl", CartService.class);
        cartService.add(new Cart(8, 4, 5, new BigDecimal(541.0), null));
    }
    @Test
    public void testUpdate(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CartService cartService = context.getBean("cartServiceImpl", CartService.class);
        cartService.update(new Cart(8, 4, 6, new BigDecimal(541.1),null ));
    }
    @Test
    public void testFindById(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CartService cartService = context.getBean("cartServiceImpl", CartService.class);
        List<Cart> cartList = cartService.findById(8);
        for (Cart cart : cartList) {
            System.out.println(cart);
        }
    }
    @Test
    public void testDeleteByUid(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CartService cartService = context.getBean("cartServiceImpl", CartService.class);
        cartService.deleteByUid(8);
    }
    @Test
    public void testDelete(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CartService cartService = context.getBean("cartServiceImpl", CartService.class);
        cartService.delete(8, 4);
    }
}
