package com.qf.web.servlet;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.Goods;
import com.qf.service.GoodsService;
import com.qf.utils.StringUtils;
import org.springframework.web.context.ContextLoader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.qf.pojo.PageBean;

@WebServlet(name = "GoodsServlet", value = "/goodsServlet")
public class GoodsServlet extends BaseServlet {
    //创建service对象
    private GoodsService goodsService = (GoodsService) ContextLoader.getCurrentWebApplicationContext().getBean("goodsServiceImpl");

    public String getGoodsListByTypeId(HttpServletRequest request, HttpServletResponse response) {
        //接收数据
        //接收前端传来的物品类型id
        String typeId = request.getParameter("typeId");
        //第一次点击没有传 使用默认数据
        //接收页码
        String _pageNum = request.getParameter("pageNum");
        //接收显示的数据条数
        String _pageSize = request.getParameter("pageSize");
        System.out.println(typeId);
//        System.out.println(_pageNum);
//        System.out.println(_pageSize);
        int pageNum = 1;
        int pageSize = 8;
        //判断下传来的pagenum和pagesize是否为空
        if (!StringUtils.isEmpty(_pageNum)) {   //如果传来的数据不为空
            pageNum = Integer.parseInt(_pageNum);
            if (pageNum < 1) {
                pageNum = 1;
            }
        }
        if (!StringUtils.isEmpty(_pageSize)) {
            pageSize = Integer.parseInt(_pageSize);
            if (pageSize < 1) {
                pageSize = 8;
            }

        }
        //封装下条件
        String condition = "";
        if (!StringUtils.isEmpty(typeId)) {
            condition = "typeid=" + typeId;
        }

        //执行方法
        try {
            System.out.println(condition);
            PageHelper.startPage(pageNum, pageSize);
            PageInfo<Goods> pageInfo = goodsService.findPageByWhere(condition);
//            System.out.println(pageInfo.getNavigateFirstPage());
//            System.out.println(pageInfo.getNavigateLastPage());
//            System.out.println(pageInfo.getPages());
//            System.out.println(pageInfo.getPageNum());
//            System.out.println(pageInfo.getPageSize());
//            System.out.println(pageInfo.getTotal());
            //PageBean<Goods> pageBean = goodsService.findPageByWhere(pageNum, pageSize, condition);
            //System.out.println(pageBean.toString());
            request.setAttribute("pageBean", pageInfo);
            request.setAttribute("typeId", Integer.parseInt(typeId));
            //转发
            return "/goodsList.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "redirect:/index.jsp";
        }
//        try {
//            PageBean<Goods> pageBean=goodsService.findPageByWhere(pageNum,pageSize,condition);  // typeId=1;
//            request.setAttribute("pageBean", pageBean);
//            return "/goodsList.jsp";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "redirect:/index.jsp";
//        }
    }

    public String getGoodsById(HttpServletRequest request, HttpServletResponse response){
        //根据id查询物品的详细信息
        String id = request.getParameter("id");
        //判断id是否为空
        if (StringUtils.isEmpty(id)){
            return "redirect:/index.jsp";
        }

        Goods goods = goodsService.findById(Integer.parseInt(id));
        request.setAttribute("goods", goods);
        //转发
        return "/goodsDetail.jsp";
    }

}

