package Service;



import Dao.UserAccountDao;
import Domain.UserDom;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * @author ding
 * 用户操作数据处理服务
 */
public class UserDomService {



    /**
     * 用户登录处理
     * @param user 用户实体类
     * @return 是否允许登录
     */
    public UserDom login(UserDom user)  {
        UserAccountDao userAccountDao=new UserAccountDao();

        UserDom userDom1=userAccountDao.queryUserByName(user.getName());
        if(userDom1.getName()==null || userDom1.getPassword()==null)
        {
            return null;
        }
        String name=userDom1.getPassword();
        name.replace(" ","");
        if(name.length()>1){
            if(name.equals(user.getPassword())){
                return userDom1;
            }else {
                return null;
            }

        }else {
            return null;
        }
    }

    /**
     * 注册
     * @param user
     * @return
     */
    public boolean register(UserDom user){
        UserAccountDao userAccountDao=new UserAccountDao();

        if(userAccountDao.insertUser(user)){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 修改信息
     * @param user
     * @return
     */
    public boolean compile(UserDom user){
        UserAccountDao userAccountDao=new UserAccountDao();

        if(userAccountDao.updatainfo(user)){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 注销
     * @param user
     * @return
     */
    public boolean logout(UserDom user){
        UserAccountDao userAccountDao=new UserAccountDao();

        if(userAccountDao.deleteuser(user)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 查询信息
     * @param name
     * @return
     */
    public UserDom select(String name){
        if(name==null){
            return null;
        }

        UserAccountDao userAccountDao=new UserAccountDao();
        UserDom userDom=userAccountDao.queryUserByName(name);

        if(userDom.getName()!=null){
            return userDom;
        }

        return null;

    }


    /**
     * 取款
     * @param ID
     * @param moeny
     * @return
     */
    public boolean withdrawal(String ID,String moeny){
        int id=Integer.parseInt(ID);
        float withdrawalmoeny=Float.parseFloat(moeny);
        UserAccountDao userAccountDao=new UserAccountDao();

        return userAccountDao.updataMoney(id,withdrawalmoeny);
    }

    public boolean transferaccount(String oppid,String seid,String tmoney){
        UserAccountDao userAccountDao=new UserAccountDao();
        int oppsiteid=Integer.parseInt(oppid);
        int selfid=Integer.parseInt(seid);
        int money=Integer.parseInt(tmoney);


        return userAccountDao.transferaccount(oppsiteid,selfid,money);
    }
}
