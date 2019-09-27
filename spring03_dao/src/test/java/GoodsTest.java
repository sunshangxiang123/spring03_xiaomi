import com.qf.dao.GoodsMapper;
import com.qf.pojo.Goods;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class GoodsTest {
//    @Test
//    public void testGetTotalCount(){
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        GoodsMapper goodsMapper = context.getBean("goodsMapper", GoodsMapper.class);
//        long count = goodsMapper.getTotalCount("typeid='1'");
//        System.out.println(count);
//    }
    @Test
    public void testFindPageByWhere(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        GoodsMapper goodsMapper = context.getBean("goodsMapper", GoodsMapper.class);
        List<Goods> goodsList = goodsMapper.findPageByWhere("typeId='1'");
        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
    }
    @Test
    public void testFindById(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        GoodsMapper goodsMapper = context.getBean("goodsMapper", GoodsMapper.class);
        Goods goods = goodsMapper.findById(1);
        System.out.println(goods);
    }
}
