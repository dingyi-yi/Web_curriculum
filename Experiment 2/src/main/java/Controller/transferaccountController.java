package Controller;

import Service.UserDomService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ding
 * 转账
 */
@WebServlet(name = "transferaccount",urlPatterns = "/transferaccount",loadOnStartup = 1)
public class transferaccountController extends HttpServlet {
    public transferaccountController(){
        super();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        String strselfid=request.getParameter("selfid");
        String stroppsiteid=request.getParameter("oppositeid");
        String strmoney=request.getParameter("money");

        UserDomService userDomService=new UserDomService();
        PrintWriter out;
        try {
            if(userDomService.transferaccount(stroppsiteid,strselfid,strmoney)){
                String data="{\"state\":\"true\"}";
                out = response.getWriter();
                out.print(data);
            }else {
                String data="{\"state\":\"false\"}";
                out = response.getWriter();
                out.print(data);
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
