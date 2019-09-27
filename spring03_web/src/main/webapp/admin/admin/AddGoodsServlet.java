package com.qf.web.admin;

import com.qf.domain.GoodsType;
import com.qf.service.GoodsTypeService;
import com.qf.service.impl.GoodsTypeServiceImpl;
import com.qf.web.servlet.BaseServlet;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@MultipartConfig()
@WebServlet(name = "AddGoodsServlet",value = "/addGoodsServlet")
public class AddGoodsServlet extends BaseServlet {
//    public String addGoods(HttpServletRequest request,HttpServletResponse response) {
//        //判断是否登录
//        User admin = (User) request.getSession().getAttribute("admin");
//        if (admin==null){
//            return "/admin/login.jsp";
//        }
//        //处理数据
//
//        Collection<Part> parts = null;
//        try {
//            parts = request.getParts();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (parts!=null&&parts.size()>0){
//            for (Part part : parts) {
//                //判断是普通项还是文件
//                String filename= part.getSubmittedFileName();
//                System.out.println(part.getName());
//                Goods goods = new Goods();
//                ConvertUtils.register(new Converter() {
//                    @Override
//                    public <T> T convert(Class<T> aClass, Object o) {
//                        if (o==null){
//                            return null;
//                        }
//                        //判断o是什么类型
//                        if (o instanceof String){
//                            String str = (String) o;
//                            SimpleDateFormat[] sdfs = {
//                                    new SimpleDateFormat("yyyy-MM-dd "),
//                                    new SimpleDateFormat("yyyy/MM/dd "),
//                                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
//                                    new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
//                            };
//                            for (SimpleDateFormat sdf : sdfs) {
//                                try {
//                                    return (T) sdf.parse(str );
//                                } catch (ParseException e) {
//                                    e.printStackTrace();
//                                    continue;
//                                }
//                            }
//                        }
//                        return null;
//                    }
//                }, Date.class);
//                ConvertUtils.register(new Converter() {
//                    @Override
//                    public <T> T convert(Class<T> aClass, Object o) {
//                        return (T) new BigDecimal(String.valueOf(o));
//                    }
//                }, BigDecimal.class);
//
//                try {
//                    BeanUtils.populate(goods, request.getParameterMap());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                System.out.println(goods.toString());
//
//                if (filename==null){
//                    //普通项
//
//                }else {
//                    //文件
//                }
//            }
//        }
//        return null;
//    }
    public String goodsTypeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("application/json;charset=utf-8");
        //获取商品的类型
        GoodsTypeService goodsTypeService = new GoodfTypeServiceImpl();
        //调用方法
        List<GoodsType> goodsTypeList= goodsTypeService.getTypeByLevel(1);
        //那list转成list字符串
        //把good托儿list也放在域里面
        request.getSession().setAttribute("goodsTypeList", goodsTypeList);
//        String json = JSON.toJSONString(goodsTypeList);
//        //返回到浏览器
//        response.getWriter().write(json);
        return "redirect:/admin/addGoods.jsp";
    }
}
