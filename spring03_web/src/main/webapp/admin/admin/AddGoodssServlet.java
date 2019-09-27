package com.qf.web.admin;

import com.qf.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "AddGoodssServlet",value = "/addgoodsSServlet")
public class AddGoodssServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断是否登录
        User admin = (User) request.getSession().getAttribute("admin");
        if (admin==null){
            request.getRequestDispatcher(request.getContextPath()+"/admin/login.jsp").forward(request, response);
            return;
        }
        //接受数据
        Collection<Part> parts = request.getParts();
        if (parts!=null&&parts.size()!=0){
            for (Part part : parts) {
                String fileName = part.getSubmittedFileName();
                System.out.println(fileName);
                if (fileName!=null){
                    //文件
                }else {
                    //普通数据

                }

            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
