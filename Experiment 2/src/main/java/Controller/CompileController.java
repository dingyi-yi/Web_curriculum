package Controller;

import Domain.UserDom;
import Service.UserDomService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ding
 */
@WebServlet(name = "compile", urlPatterns = "/compile" ,loadOnStartup = 1)
public class CompileController extends HttpServlet {

    public CompileController(){
        super();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        /*
        获取参数
         */
        String name=request.getParameter("name");
        String birthday=request.getParameter("birthday");
        String gender=request.getParameter("gender");

        /*
        构造USER
         */
        UserDom userDom=new UserDom();
        userDom.setName(name);
        userDom.setBdata(birthday);
        userDom.setGender(gender);


        UserDomService userDomService=new UserDomService();

       try {
           response.setCharacterEncoding("UTF-8");
            if(userDomService.compile(userDom)){
                request.getSession().setAttribute("user",userDom);

                String result="{\"state\":0}";
                PrintWriter out;
                out = response.getWriter();
                out.print(result);
                out.close();
            }else {
                PrintWriter out = null;
                out = response.getWriter();
                out.print(false);
                out.close();
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
