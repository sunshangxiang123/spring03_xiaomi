import com.qf.dao.GoodsTypeMapper;
import com.qf.pojo.GoodsType;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class GoodsTypeTest {
    @Test
    public void testFindTypeByLevel() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        GoodsTypeMapper goodsTypeMapper = context.getBean("goodsTypeMapper", GoodsTypeMapper.class);
        List<GoodsType> goodsTypeList = goodsTypeMapper.findTypeByLevel(1);
        for (GoodsType goodsType : goodsTypeList) {
            System.out.println(goodsType);
        }
    }
    @Test
    public void testFindById() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        GoodsTypeMapper goodsTypeMapper = context.getBean("goodsTypeMapper", GoodsTypeMapper.class);
        GoodsType goodsType = goodsTypeMapper.findById(7);
        System.out.println(goodsType);
    }
}
