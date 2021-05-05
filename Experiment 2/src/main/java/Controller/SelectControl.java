package Controller;

import Domain.UserDom;
import Service.UserDomService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "select",urlPatterns = "/select",loadOnStartup = 1)
public class SelectControl extends HttpServlet {
    public SelectControl(){
        super();
    }



    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        //获取参数
        String name=request.getParameter("name");

        UserDomService userDomService=new UserDomService();


        UserDom userDom=userDomService.select(name);

        request.getSession().setAttribute("user",userDom);

        String  data="{\"id\":\""+userDom.getId()+"\",\"name\":\""+userDom.getName()+"\",\"genger\":\""+userDom.getGender()+"\",\"birthday\":\""+
                        userDom.getBdata()+"\",\"money\":\""+userDom.getMoney()+"\"}";
        PrintWriter out;
        try {

            out = response.getWriter();
            out.print(data);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
