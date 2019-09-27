package com.qf.web.servlet;

import com.qf.pojo.Cart;
import com.qf.pojo.Goods;
import com.qf.pojo.User;
import com.qf.service.CartService;
import com.qf.service.GoodsService;
import com.qf.utils.StringUtils;
import org.springframework.web.context.ContextLoader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

//cartservlet?method=addCart&goodsId=1&number=1
@WebServlet(name = "CartServlet",value = "/cartServlet")
public class CartServlet extends BaseServlet {


    private CartService cartService = (CartService) ContextLoader.getCurrentWebApplicationContext().getBean("cartServiceImpl");
    private GoodsService goodsService = (GoodsService) ContextLoader.getCurrentWebApplicationContext().getBean("goodsServiceImpl");


    public String addCart(HttpServletRequest request, HttpServletResponse response){
        String goodsId = request.getParameter("goodsId");
        String number = request.getParameter("number");
        User user = (User) request.getSession().getAttribute("user");
        //判断下用户是否存在 如果不存在重定向到登录界面
        if (user==null){
            return "redirect:/login.jsp";
        }
        //判断下商品编号是否为空
        if (StringUtils.isEmpty(goodsId)){
            return "redirect:/index.jsp";
        }

        Integer id = user.getId();

        //四个属性 用户id 数量 价格
        //根据goodsid查询商品的单价
        Goods goods = goodsService.findById(Integer.parseInt(goodsId));
        BigDecimal price = goods.getPrice();
        //先查询瞎购物车中是否有这个物品 根据用户id 商品id

        Cart cart =  cartService.findByIdAndGid(id,Integer.parseInt(goodsId));
        //判断购物车是否为空
        try {
            if (cart==null){
                //添加数据
                Cart cart1 = new Cart(id, Integer.parseInt(goodsId), Integer.parseInt(number),price.multiply(new BigDecimal(Integer.parseInt(number))),null);
                cartService.add(cart1);


            }else {
                //修改数据
                cart.setNum(cart.getNum()+Integer.parseInt(number));
                cart.setMoney(price.multiply(new BigDecimal(cart.getNum())));
                cartService.update(cart);

            }
            return "redirect:/cartSuccess.jsp";
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "redirect:/index,jsp";
        }

    }

    public String getCart(HttpServletRequest request, HttpServletResponse response){
        //根据用户名查询所有的商品
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/login.jsp";
        }
        List<Cart> carts =  cartService.findById(user.getId());
        request.setAttribute("cart", carts);
        return "/cart.jsp";
    }
    public String addCartAjax(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/login.jsp";
        }
        //获取前端传来的数据
        String goodsId = request.getParameter("goodsId");
        String number = request.getParameter("number");
        //根据用户id 和商品id查询购物车
        Cart cart = cartService.findByIdAndGid(user.getId(), Integer.parseInt(goodsId));
        if (cart!=null){
            if (number.equals("0")){
                //删除
                cartService.delete(user.getId(),Integer.parseInt(goodsId));

            }else {
                //更新
                int num = Integer.parseInt(number);
                //获取单价
                BigDecimal price = cart.getMoney().divide(new BigDecimal(cart.getNum()));
                //更新数量
                cart.setNum(cart.getNum()+num);
                //更新金额
                cart.setMoney(price.multiply(new BigDecimal(cart.getNum())));
                //执行更新
                cartService.update(cart);
            }
        }
        //如果购物车不为空
        return null;

    }

    public String clearCartAjax(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/login.jsp";
        }
        cartService.deleteByUid(user.getId());
        return null;

    }
}
