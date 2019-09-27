package com.qf.web.filter;

import com.qf.pojo.User;
import com.qf.service.UserService;
import com.qf.utils.Base64Utils;
import org.springframework.web.context.ContextLoader;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AutoLoginFilter",value = "/index.jsp")
public class AutoLoginFilter implements Filter {
    private UserService userService = (UserService) ContextLoader.getCurrentWebApplicationContext().getBean("userServiceImpl");
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //判断当前是否已经登录
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        User user = (User) request.getSession().getAttribute("user");
        if (user!=null){
            chain.doFilter(req, resp);
            return;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userInfo")){
                    String value = cookie.getValue();
                    String userInfo = Base64Utils.decode(value);
                    String[] userInfos = userInfo.split("#");
                    //判断密码是否改了
                    User user1 = userService.login(userInfos[0], userInfos[1]);
                    if (user1!=null){
                        if (user1.getFlag()==1){
                            request.getSession().setAttribute("user", user1);
                            chain.doFilter(req, resp);
                            return;
                        }
                    }else {
                        Cookie cookie1 = new Cookie("userInfo", "");
                        cookie1.setMaxAge(0);
                        cookie1.setPath("/");
                        response.addCookie(cookie1);
                    }
                }
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
