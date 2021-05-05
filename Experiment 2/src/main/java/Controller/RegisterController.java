package Controller;

import Domain.UserDom;
import Service.UserDomService;
import Utils.CreateIDCode;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author ding
 * 注册前端控制
 */
@WebServlet(name = "register", urlPatterns = "/register",loadOnStartup = 1)
public class RegisterController extends HttpServlet {

    public RegisterController(){
        super();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        /*
        获取参数
         */
        String name=request.getParameter("name");
        String password=request.getParameter("password");



        /*
        构造用户实体
         */

        UserDom userDom=new UserDom();
        userDom.setId(CreateIDCode.getUUIDInOrderId());
        userDom.setName(name);
        userDom.setBdata("2020-12-12");
        userDom.setPassword(password);
        userDom.setGender("男");
        userDom.setMoney(0);


        UserDomService userDomService=new UserDomService();

            try {
                if(userDomService.register(userDom)){
                    request.getSession().setAttribute("user",userDom);
                    response.sendRedirect("/main.jsp");
                }else {
                    response.sendRedirect("/register.jsp");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        doGet(request,response);
    }

}
