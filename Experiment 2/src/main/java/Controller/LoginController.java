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
 * 登录
 */
@WebServlet(name = "login",urlPatterns = "/login",loadOnStartup = 1)
public class LoginController extends HttpServlet  {

    public LoginController(){
        super();
    }

    /**
     * 处理get请求
     * @param request 请求
     * @param response 响应
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){



        /*
        获取参数
         */
        String name=request.getParameter("name");
        String password=request.getParameter("password");

        /*
        构造用户实体
         */
        UserDom userDom=new UserDom();
        userDom.setName(name);
        userDom.setPassword(password);

        UserDomService userDomService=new UserDomService();

        try {
            /*
            判断是否可以登录
             */
            UserDom user=userDomService.login(userDom);
            if(user!=null)
            {
                request.getSession().setAttribute("user",user);
                response.sendRedirect("/main.jsp");

            }else {
                response.sendRedirect("/login.html");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response ){
        doGet(request,response);
    }

}
