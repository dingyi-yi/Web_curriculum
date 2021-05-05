package Dao;
import Common.ComConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author ding
 */
public class DaoBase {

    public Connection conn=null;
    public Connection connect() throws ClassNotFoundException, SQLException {
        //加载驱动
        Class.forName(ComConfig.DATABASEDRIVER);
        //连接
        conn = DriverManager.getConnection(ComConfig.DATABASEURL, ComConfig.DATABASEUSER, ComConfig.DATABASEPASSWORD);
        if(conn!=null){
            return conn;
        }else {
            return null;
        }

    }

    public void connectionclose(){
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
