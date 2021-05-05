package Controller;

import Domain.UserDom;
import Service.UserDomService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

/**
 * @author ding
 */
@WebServlet(name = "logout",urlPatterns = "/logout",loadOnStartup = 1)
public class LogoutController extends HttpServlet {

    public LogoutController() {
        super();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        //获取参数
        String name=request.getParameter("name");

        //构造
        UserDom userDom=new UserDom();
        userDom.setName(name);
        UserDomService userDomService=new UserDomService();
        try {
            if(userDomService.logout(userDom)){
                request.getSession().setAttribute("user",userDom);
                response.sendRedirect("/login.html");
            }else {
                response.sendRedirect("/register.html");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request,response);
    }
}