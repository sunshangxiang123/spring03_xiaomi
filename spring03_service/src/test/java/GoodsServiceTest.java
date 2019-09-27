import com.qf.dao.GoodsMapper;
import com.qf.pojo.Goods;
import com.qf.service.GoodsService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class GoodsServiceTest {
    @Test
    public void testFindById(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        GoodsService goodsService = context.getBean("goodsServiceImpl", GoodsService.class);
        Goods goods = goodsService.findById(1);
        System.out.println(goods);
    }
    @Test
    public void testFindPageByWhere(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        GoodsMapper goodsMapper = context.getBean("goodsMapper", GoodsMapper.class);
        List<Goods> goodsList = goodsMapper.findPageByWhere("typeId=1");
        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
    }
}
