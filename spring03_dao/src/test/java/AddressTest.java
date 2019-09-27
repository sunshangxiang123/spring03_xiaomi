import com.qf.dao.AddressMapper;
import com.qf.pojo.Address;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AddressTest {
    @Test
    public void testFindById(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AddressMapper addressMapper = context.getBean("addressMapper", AddressMapper.class);
        List<Address> addressList = addressMapper.findById(8);
        for (Address address : addressList) {
            System.out.println(address);
        }
    }
    @Test
    public void testAdd(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AddressMapper addressMapper = context.getBean("addressMapper", AddressMapper.class);
        addressMapper.add(new Address(8, "北京", "冯军伟", "13132323322", 8, 0));
    }
    @Test
    public void testUpdateDefault(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AddressMapper addressMapper = context.getBean("addressMapper", AddressMapper.class);
        addressMapper.updateDefault(8, 8);
    }
    @Test
    public void testDeleteAddress(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AddressMapper addressMapper = context.getBean("addressMapper", AddressMapper.class);
        addressMapper.deleteAddress(9);
    }
    @Test
    public void testUpdate(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AddressMapper addressMapper = context.getBean("addressMapper", AddressMapper.class);
        addressMapper.update(new Address(7, "上海", "冯军伟", "6546464", 8, 0));
    }
    @Test
    public void testFindByAid(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AddressMapper addressMapper = context.getBean("addressMapper", AddressMapper.class);
        Address address = addressMapper.findByAid(7);
        System.out.println(address);
    }
}
