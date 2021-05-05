package Controller;

import Dao.UserAccountDao;
import Service.UserDomService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ding
 * 取款
 */
@WebServlet(name = "withdrawal",urlPatterns = "/withdrawal",loadOnStartup = 1)
public class WithdrawalController extends HttpServlet {
    public WithdrawalController(){
        super();
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String ID=request.getParameter("id");
        String withdrawalMoney=request.getParameter("withdrawalMoney");

        UserDomService userDomService=new UserDomService();
        PrintWriter out;
        try {
            if(userDomService.withdrawal(ID,withdrawalMoney)){
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
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request,response);
    }
}
