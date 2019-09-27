package com.qf.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

//@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断用户的行为
        String methodName = request.getParameter("method");
        try {
            //获取方法
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法 请求转发和请求重定向的时候 有地址 返回值
            String url = (String) method.invoke(this, request, response);
            //如果有返回值
            if (url != null && url.trim().length() != 0) {
                //判断是否是重定向
                if (url.startsWith("redirect:")) {
                    //重定向
                    response.sendRedirect(request.getContextPath() + url.split(":")[1]);
                } else {
                    //转发
                    request.getRequestDispatcher(url).forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
