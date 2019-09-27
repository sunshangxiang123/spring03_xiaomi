package com.qf.web.servlet;

import com.qf.pojo.*;
import com.qf.service.AddressService;
import com.qf.service.CartService;
import com.qf.service.OrderService;
import com.qf.service.impl.OrderServiceImpl;
import com.qf.utils.RandomUtils;
import org.springframework.web.context.ContextLoader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "OrderServlet",value = "/orderServlet")
public class OrderServlet extends BaseServlet {
    private CartService cartService = (CartService) ContextLoader.getCurrentWebApplicationContext().getBean("cartServiceImpl");
    private OrderService orderService = (OrderService) ContextLoader.getCurrentWebApplicationContext().getBean("orderServiceImpl");
    private AddressService addressService = (AddressService) ContextLoader.getCurrentWebApplicationContext().getBean("addressServiceImpl");


    //orderServlet?method=getOrderView


    public String getOrderView(HttpServletRequest request, HttpServletResponse response ){
        //首先判断是否登录了
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/login.jsp";
        }
        //根基用户id查询所有商品
        List<Cart> carts = cartService.findById(user.getId());
        //把购物车的信息放进域中
        request.setAttribute("carts", carts);
        //然后查询一下地址
        List<Address> addressList= addressService.findById(user.getId());
        //把查询到的地址放到域中
        request.setAttribute("addList",addressList );
        return "/order.jsp";

    }

    public String addOrder(HttpServletRequest request, HttpServletResponse response ){
        //首先判断是否登录了
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/login.jsp";
        }
        //获取传来的id 地址id
        String aid = request.getParameter("aid");
        //根据地址用户胡ID查询购物车中测内容
        List<Cart> carts = cartService.findById(user.getId());
        //生成一个订单编号
        //如果购物车为空 防止重复生成订单
        if(carts==null||carts.size()==0){

            request.setAttribute("msg", "购物车为空，请选择商品!");
            return "/message.jsp";
        }
        String orderId = RandomUtils.createOrderId();

        //便利购物车集合获得总钱数
        List<OrderDetail> orderDetailList = new ArrayList<>();
        BigDecimal sum = new BigDecimal(0);
        for (Cart cart : carts) {
            //创建订单详情表
            OrderDetail orderDetail = new OrderDetail(null, orderId, cart.getPid() , cart.getNum(), cart.getMoney(),null);
            orderDetailList.add(orderDetail);
            sum = sum.add(cart.getMoney());

        }
        System.out.println(orderDetailList.toString());


        //创建订单表
        Order order = new Order(orderId, user.getId(), sum, "1", new Date(), Integer.parseInt(aid),null,null);
        System.out.println(order.toString());
        OrderService orderService = new OrderServiceImpl();
        try {
            orderService.saveOrder(order,orderDetailList);
            //成功了
            //把order放进域中
            request.setAttribute("order", order);
            //转发到成功页面
            return "/orderSuccess.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            //失败了 转发到失败页面
            request.setAttribute("msg","订单提交失败" );
            return "/message.jsp";
        }

    }

    //orderServlet?method=getOrderList
    public String getOrderList(HttpServletRequest request, HttpServletResponse response ){
        //首先判断是否登录了
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/login.jsp";
        }
        //根据用户ID查询订单列表
        List<Order> orderList= orderService.findByUid(user.getId());
        System.out.println("aaaaaa"+orderList);
        request.setAttribute("orderList", orderList);
        return "/orderList.jsp";

    }
    //orderServlet?method=getOrderDetail&oid
    public String getOrderDetail(HttpServletRequest request, HttpServletResponse response){
        //首先判断是否登录了
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/login.jsp";
        }
        //接受前端传来的订单的oid
        String oid = request.getParameter("oid");
        //根据oid查询订单
        Order order =orderService.findByOid(oid);
        //把返回的结果传回前端页面
        request.setAttribute("order", order);
        return "/orderDetail.jsp";
    }




}
