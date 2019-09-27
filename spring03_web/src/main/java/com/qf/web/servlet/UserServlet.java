package com.qf.web.servlet;

import cn.dsna.util.images.ValidateCode;
import com.qf.pojo.Address;
import com.qf.pojo.User;
import com.qf.service.AddressService;
import com.qf.service.UserService;
import com.qf.utils.Base64Utils;
import com.qf.utils.RandomUtils;
import com.qf.utils.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.context.ContextLoader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet",value = "/userServlet")
public class UserServlet extends BaseServlet {
    private UserService userService = (UserService) ContextLoader.getCurrentWebApplicationContext().getBean("userServiceImpl");
    private AddressService addressService = (AddressService) ContextLoader.getCurrentWebApplicationContext().getBean("addressServiceImpl");

    //注册
    public String register(HttpServletRequest request, HttpServletResponse response){
        //用户注册
        //获取数据
        User user = new User();
        try {
            BeanUtils.populate(user,request.getParameterMap());
            //自己获取重复的密码
            String repassword = request.getParameter("repassword");

            //打印下传完数据的user
            System.out.println(user.toString());
            //校验下数据
            if (StringUtils.isEmpty(user.getUsername())){
                request.setAttribute("registerMsg", "用户名不能为空");
                return "/register.jsp";
            }
            if (StringUtils.isEmpty(user.getPassword())){
                request.setAttribute("registerMsg", "密码不能为空");
                return "/register.jsp";
            }
            if (!user.getPassword().equals(repassword)){
                request.setAttribute("registerMsg", "两次密码不一致");
                return "/register.jsp";
            }
            if (StringUtils.isEmpty(user.getEmail())){
                request.setAttribute("registerMsg", "邮箱不能为空");
                return "/register.jsp";
            }

            //flag  role code 没有赋值
            user.setFlag(0);
            user.setRole(1);
            user.setCode(RandomUtils.createActive());
            userService.register(user);
            //如果注册成功去登录
            return "redirect:/registerSuccess.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("registerMsg", "注册失败");
        }
        return "/register.jsp";
    }

    public String checkUserName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //接收前端传来的username
        String username = request.getParameter("username");
        System.out.println("username = " + username);
        //如果用户名为空直接返回null
        if (username==null||username.trim().length()==0){
            return null;
        }
        //吧得到的username 调用检查用户名的方法
        User user = userService.checkUserName(username);
        //如果没查到 返回1 查到了返回0
        if (user!=null){
            response.getWriter().write("1");
        }else {
            response.getWriter().write("0");
        }
        return null;

    }
    public String login(HttpServletRequest request, HttpServletResponse response){
        //获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //获取自动登录是否点了
        String auto = request.getParameter("auto");
        if (StringUtils.isEmpty(username)){
            request.setAttribute("msg", "用户名不能为空");
            return "/login.jsp";
        }
        if (StringUtils.isEmpty(password)){
            request.setAttribute("msg", "密码不能为空");
            return "/login.jsp";
        }
        System.out.println("用户登录");
        //验证用户名密码是否正确
        User user = userService.login(username, password);
        if (user==null){
            request.setAttribute("msg", "用户名或者密码有误");
            return "/login.jsp";
        }else {
            //输入对了
            //有没有激活
            if (user.getFlag()!=1){
                request.setAttribute("msg", "用户名尚未激活或禁用");
                return "/login.jsp";
            }
            //有没有权限
            if (user.getRole()!=1){
                request.setAttribute("msg", "用户没有权限");
                return "/login.jsp";
            }
            //登录成功
            request.getSession().setAttribute("user", user);
            //重定向到首页
            if (auto!=null){
                //创建cookie
                String userInfo = username+"#"+password;
                Cookie cookie = new Cookie("userInfo", Base64Utils.encode(userInfo));
                cookie.setMaxAge(60*60*24*14);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
            }
            return "redirect:/index.jsp";
        }


    }

    public String code(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ValidateCode validateCode = new ValidateCode(120, 30, 4, 15);
        String code = validateCode.getCode();
        request.getSession().setAttribute("vcode", code);
        validateCode.write(response.getOutputStream());
        return null;
    }
    //验证验证码是否正确
    public String checkCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String clientcode = request.getParameter("vcode");
        String servervcode = (String) request.getSession().getAttribute("vcode");
        if (StringUtils.isEmpty(clientcode)){
            return null;
        }
        if (clientcode.equalsIgnoreCase(servervcode)){
            response.getWriter().write("0");
        }else {
            response.getWriter().write("1");
        }
        return null;
    }
    public String loginOut(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("user");

        request.getSession().invalidate();

        Cookie cookie = new Cookie("userInfo", "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return "redirect:/login.jsp";

    }
    public String getAddress(HttpServletRequest request, HttpServletResponse response){
        //首先判断是否登录了
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/login.jsp";
        }
        //通过用户id查询地址

        List<Address> addressList = addressService.findById(user.getId());
        request.setAttribute("addList", addressList);
        return "/self_info.jsp";
    }
    public String addAddress(HttpServletRequest request, HttpServletResponse response){
        //首先判断是否登录了
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/login.jsp";
        }
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String detail = request.getParameter("detail");
        //创建一个地址对象
        Address address = new Address(null, detail, name, phone, user.getId(), 0);
        addressService.add(address);
        return getAddress(request, response);
    }

    public String defaultAddress(HttpServletRequest request, HttpServletResponse response){
        //首先判断是否登录了
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/login.jsp";
        }
        String id = request.getParameter("id");
        addressService.updateDefault(Integer.parseInt(id),user.getId());
        return getAddress(request, response);
    }

    public String deleteAddress(HttpServletRequest request, HttpServletResponse response){
        //首先判断是否登录了
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/login.jsp";
        }
        String id = request.getParameter("id");

        addressService.deleteAddress(Integer.parseInt(id));
        return getAddress(request, response);
    }

    public String updateAddress(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //首先判断是否登录了
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/login.jsp";
        }
        //接收前段数据
//        Address address = new Address();
//        BeanUtils.populate(address, request.getParameterMap());
//        address.setId(user.getId());
//        addressService.update(address);
        //并且判断
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String level = request.getParameter("level");
        String detail = request.getParameter("detail");
        String phone = request.getParameter("phone");
        if(StringUtils.isEmpty(name)){
            response.getWriter().write("<script type='text/javascript'>alert('收件人不能为空');window.location='userServlet?method=getAddress'</script>");
            return null;
        }
        if(StringUtils.isEmpty(phone)){
            response.getWriter().write("<script type='text/javascript'>alert('联系电话不能为空');window.location='userServlet?method=getAddress'</script>");
            return null;
        }
        if(StringUtils.isEmpty(detail)){
            response.getWriter().write("<script type='text/javascript'>alert('地址不能为空');window.location='userServlet?method=getAddress'</script>");
            return null;
        }
        //更新操作
        Address address = new Address(Integer.parseInt(id), detail, name, phone, user.getId(), Integer.parseInt(level));
        addressService.update(address);
        return  getAddress(request, response);



    }

}
