import com.qf.pojo.Address;
import com.qf.service.AddressService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AddressServiceTest {
    @Test
    public void testFindById(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AddressService addressService = context.getBean("addressServiceImpl", AddressService.class);
        List<Address> addressList = addressService.findById(8);
        for (Address address : addressList) {
            System.out.println(address);
        }
    }
    @Test
    public void testAdd(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AddressService addressService = context.getBean("addressServiceImpl", AddressService.class);
        addressService.add(new Address(9, "北京", "冯军伟", "13132323321", 8, 0));
    }
    @Test
    public void testUpdateDefault(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AddressService addressService = context.getBean("addressServiceImpl", AddressService.class);
        addressService.updateDefault(7,8 );
    }
    @Test
    public void testDeleteAddress(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AddressService addressService = context.getBean("addressServiceImpl", AddressService.class);
        addressService.deleteAddress(12);
    }
    @Test
    public void testUpdate(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AddressService addressService = context.getBean("addressServiceImpl", AddressService.class);
        addressService.update(new Address(7, "上海", "冯军伟", "6546464", 8, 0));
    }
}
