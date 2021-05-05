package Dao;




import Domain.UserDom;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ding
 */

public class UserAccountDao extends DaoBase{

    private  Connection connection=null;
    private  ResultSet resultSet=null;
    private  Statement statement=null;

    /**
     * 根据姓名查询
     * @param name 姓名
     * @return 用户实体类
     */
    public UserDom queryUserByName(String name){


        String sql="select* from UserAccount where name='"+name+"'";

        try {
            connection=super.connect();
            if(connection!=null){

                /*
                连接数据库
                 */
                statement=connection.createStatement();
                resultSet = statement.executeQuery(sql);

                /*
                查询并获取数据
                 */
                UserDom userDom=new UserDom();
                while (resultSet.next()){

                    userDom.setId(resultSet.getInt("id"));
                    userDom.setName(resultSet.getString("name"));
                    userDom.setPassword(resultSet.getString("password"));
                    userDom.setGender(resultSet.getString("gender"));
                    userDom.setBdata(resultSet.getString("birthday"));
                    userDom.setMoney(resultSet.getFloat("money"));
                }
                super.connectionclose();
                return userDom;

            }else {
                super.connectionclose();
                return null;
            }

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
        return null;
    }


    /**
     * 新增用户
     * @param userDom
     * @return
     */
    public boolean insertUser(UserDom userDom){

        String sql="insert into UserAccount values("+userDom.getId()+",'"+userDom.getName()+"','"+userDom.getPassword()+"','"+
                userDom.getGender()+"','"+userDom.getBdata()+"','"+userDom.getMoney()+"')";
        try {
            connection=super.connect();
            if(connection!=null){
                /*
                连接数据库
                 */
                statement=connection.createStatement();

                int result = statement.executeUpdate(sql);
                if (result==1){
                    super.connectionclose();
                    return true;
                }else{
                    super.connectionclose();
                    return false;
                }
            }else {
                super.connectionclose();
                return false;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return true;
    }


    /**
     * 修改用户信息
     * @param userDom
     * @return
     */
    public boolean updatainfo(UserDom userDom){
        String sql="update  UserAccount set gender='"+userDom.getGender()+
                "',birthday='"+userDom.getBdata()+"' where name='"+userDom.getName()+"'";
        return updata(sql);
    }


    /**
     * 删除用户
     * @param userDom
     * @return
     */
    public boolean deleteuser(UserDom userDom){
        String sql="delete from UserAccount where name='"+userDom.getName()+"'";
        try {
            connection=super.connect();
            if(connection!=null){
                /*
                连接数据库
                 */
                statement=connection.createStatement();

                int result = statement.executeUpdate(sql);
                if (result==1){
                    super.connectionclose();
                    return true;
                }else{
                    super.connectionclose();
                    return false;
                }
            }else {
                super.connectionclose();
                return false;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        super.connectionclose();
        return true;
    }


    /**
     * 取款更新余额
     * @param id
     * @param withdrawalmoney
     * @return
     */
    public boolean updataMoney(int id,float withdrawalmoney){
        String sql="update UserAccount set money=money-"+withdrawalmoney+" where id="+id;
        System.out.println(sql);
        return updata(sql);
    }

    /**
     * 转账
     * @return
     */
    public boolean transferaccount(int oppid,int selfid,float money){
        String oppsql="update UserAccount set money=money+"+money+"where id="+oppid;
        String selfsql="update UserAccount set money=money-"+money+"where id="+selfid;
        boolean self=updata(selfsql);
        if(self!=true){
            return false;
        }
        boolean opp=updata(oppsql);
        if(opp!=true){
            return false;
        }
        return true;

    }

    /**
     * 数据库更新
     * @param sql
     * @return
     */
    public boolean updata(String sql){
        try {
            connection=super.connect();
            if(connection!=null){
                /*
                连接数据库
                 */
                statement=connection.createStatement();

                int result = statement.executeUpdate(sql);
                if (result==1){
                    super.connectionclose();
                    return true;
                }else{
                    super.connectionclose();
                    return false;
                }
            }else {
                super.connectionclose();
                return false;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return true;
    }



}
