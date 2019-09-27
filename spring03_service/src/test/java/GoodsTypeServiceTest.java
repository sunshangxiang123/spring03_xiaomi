import com.qf.pojo.GoodsType;
import com.qf.service.GoodsTypeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class GoodsTypeServiceTest {
    @Test
    public void testGetTypeByLevel(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        GoodsTypeService goodsTypeService = context.getBean("goodsTypeServiceImpl", GoodsTypeService.class);
        List<GoodsType> goodsTypeList = goodsTypeService.getTypeByLevel(1);
        for (GoodsType goodsType : goodsTypeList) {
            System.out.println(goodsType);
        }
    }
    @Test
    public void testFindById(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        GoodsTypeService goodsTypeService = context.getBean("goodsTypeServiceImpl", GoodsTypeService.class);
        GoodsType goodsType = goodsTypeService.findById(5);
        System.out.println(goodsType);
    }

}
