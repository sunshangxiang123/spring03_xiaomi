package com.qf.web.admin;

import com.qf.domain.Goods;
import com.qf.domain.PageBean;
import com.qf.domain.User;
import com.qf.service.GoodsService;
import com.qf.service.impl.GoodsServiceImpl;
import com.qf.utils.StringUtils;
import com.qf.web.servlet.BaseServlet;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@MultipartConfig()
@WebServlet(name = "GoodssServlet",value = "/admin/goodssServlet")
public class GoodssServlet extends BaseServlet {
    public String getGoodsList(HttpServletRequest request, HttpServletResponse response){
        //判断有没有登录
        User admin = (User) request.getSession().getAttribute("admin");
        if (admin==null){
            return "/admin/login.jsp";
        }
        //接受前端传来的参数
        String _pageNum = request.getParameter("pageNum");
        String _pageSize = request.getParameter("pageSize");
        int pageNum = 1;
        int pageSize = 4;

        if (!StringUtils.isEmpty(_pageNum)){
            pageNum= Integer.parseInt(_pageNum);
            if (pageNum<1){
                pageNum=1;
            }
        }
        if (!StringUtils.isEmpty(_pageSize)){
            pageSize= Integer.parseInt(_pageSize);
        }
        System.out.println("aaaaaaaaaaaaaaaaaa"+pageNum+pageSize);
        //接受前端传来的商品名称和上架时间
        String name = request.getParameter("name");
        String pudate = request.getParameter("pudate");
        String condition = " 1 = 1";
        if (!StringUtils.isEmpty(name)){
            condition = condition +" and name like '%"+name+"%'";
            request.setAttribute("name", name);
        }
        if (!StringUtils.isEmpty(pudate)){
            condition = condition+" and pudate = '"+pudate+"'";
            request.setAttribute("pudate", pudate);
        }

        //查询所有商品
        GoodsService goodsService = new GoodsServiceImpl();
        PageBean<Goods> pageBean= goodsService.findPageByWhere(pageNum, pageSize,condition);
        System.out.println(pageBean);
        request.setAttribute("pagebean", pageBean);
        return "/admin/showGoods.jsp";

    }


}
