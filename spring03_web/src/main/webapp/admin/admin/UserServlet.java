package com.qf.web.admin;

import com.alibaba.fastjson.JSON;
import com.qf.domain.PageBean;
import com.qf.domain.User;
import com.qf.service.UserService;
import com.qf.service.impl.UserServiceImpl;
import com.qf.utils.StringUtils;
import com.qf.web.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UserServlet",value = "/admin/userServlet")
public class UserServlet extends BaseServlet {


    public String getUserList(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //判断有没有登录
        User admin = (User) request.getSession().getAttribute("admin");
        if (admin==null){
            return "/admin/login.jsp";
        }


        response.setContentType("text/html;charset=utf-8");
        String _pageNum = request.getParameter("pageNum");
        String _pageSize = request.getParameter("pageSize");
        //接受传递的搜索的信息
        String username = request.getParameter("username");
        String gender = request.getParameter("gender");


        int pageNum = 1;
        int pageSize = 4;
        if (!StringUtils.isEmpty(_pageNum)){
            pageNum = Integer.parseInt(_pageNum);
        }
        if (!StringUtils.isEmpty(_pageSize)){
            pageSize = Integer.parseInt(_pageSize);
        }
        //传入条件
        String condition = " where flag!= 2 ";
        //对条件进行增加
        if (!StringUtils.isEmpty(username)){
            condition = condition+" and username like '%"+username+"%'";
        }
        if (!StringUtils.isEmpty(gender)){
            condition = condition+ " and gender='"+gender+"'";
        }
        System.out.println(condition);
        //判断有没有登录
        //查询所有的有效的会员信息   添加分页查询
        UserService userService = new UserServiceImpl();
        PageBean<User> pageBean = userService.findByPage(pageNum,pageSize,condition );
        //System.out.println(pageBean);


        //List<User> userList= userService.findAll();
        //把查到的结果房间json字符串内
        //String json = JSON.toJSONString(userList);

        String json = JSON.toJSONString(pageBean);
        response.getWriter().write(json);
        return null;
    }

    public String login(HttpServletRequest request,HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService userService = new UserServiceImpl();
        User admin =userService.login(username, password);
        System.out.println("管理员"+admin);
        if(admin.getRole()!=0){
            request.setAttribute("msg", "请用管理员账号登录");
            return "/admin/login.jsp";
        }
        request.getSession().setAttribute("admin", admin);
        //把admin放进session里面
        return "/admin/admin.jsp";

    }
    public String deleteUser(HttpServletRequest request,HttpServletResponse response){

        //首先判断有没有登录
        //判断有没有登录
        User admin = (User) request.getSession().getAttribute("admin");
        if (admin==null){
            return "/admin/login.jsp";
        }
        //接受前端传来的用户ID
        String uid = request.getParameter("id");
        //根据用户ID去更新状态  flag=2 为失效
        UserService userService = new UserServiceImpl();
        userService.updateFlag(Integer.parseInt(uid));

        return null;
    }
    public String loginOut(HttpServletRequest request,HttpServletResponse response){
        //首先判断有没有登录
        User admin = (User) request.getSession().getAttribute("admin");
        if (admin==null){
            return "/admin/login.jsp";
        }
        //清除用户
        request.getSession().removeAttribute("admin");
        //让session失效
        request.getSession().invalidate();
        // 返回登录界面就好了
        return "redirect:/admin/login.jsp";


    }
}
