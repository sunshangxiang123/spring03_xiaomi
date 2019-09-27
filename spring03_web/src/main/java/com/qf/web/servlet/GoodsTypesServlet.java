package com.qf.web.servlet;

import com.alibaba.fastjson.JSON;
import com.qf.pojo.GoodsType;
import com.qf.service.GoodsTypeService;
import org.springframework.web.context.ContextLoader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GoodsTypesServlet",value = "/goodsTypesServlet")
public class GoodsTypesServlet extends BaseServlet {
    private GoodsTypeService goodsTypeService = (GoodsTypeService) ContextLoader.getCurrentWebApplicationContext().getBean("goodsTypeServiceImpl");

    public String goodsTypeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        //获取商品的类型
        //调用方法
        List<GoodsType> goodsTypeList= goodsTypeService.getTypeByLevel(1);
        //那list转成list字符串
        //把good托儿list也放在域里面
        //request.getSession().setAttribute("goodsTypeList", goodsTypeList);
        String json = JSON.toJSONString(goodsTypeList);
        //返回到浏览器
        response.getWriter().write(json);
        return null;
    }
}
