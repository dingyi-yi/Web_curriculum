package com.service;

import com.Entity.UserEntity;
import com.Uitls.CreateIDCode;
import com.alibaba.fastjson.JSONObject;
import com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ding
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;


    /**
     * 登录处理
     * @param name
     * @param password
     * @return
     */
    public UserEntity loginService(String name,String password){

        //根据姓名查询
        UserEntity userEntity=userMapper.selectByName(name);


        if(userEntity==null){
            //如果查不到，返回空
            return null;
        }else {
            //除去空格
            String truepassword=userEntity.getPassword().replace(" ","");
            if(truepassword.equals(password)) {
                //密码正确
                return userEntity;
            }else {
                //密码错误
                return null;
            }
        }


    }


    /**
     * 注册
     * @param name
     * @param password
     * @return
     */
    public UserEntity register(String name,String password){


        if(name==null || password==null ||name.equals("")||password.equals("")){
            return null;
        }


        UserEntity checkuser=userMapper.selectByName(name);
        if(checkuser!=null){
            //防止重复注册
            return checkuser;
        }else {
             /*
        构造实体类
         */
            UserEntity userEntity=new UserEntity();
            userEntity.setName(name);
            userEntity.setPassword(password);
            userEntity.setGender("男");
            Date day = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            userEntity.setBirthday(df.format(day));
            userEntity.setMoney(0);
            userEntity.setId(CreateIDCode.getUUIDInOrderId());

            //写入数据库
            int res=userMapper.insertUser(userEntity);
            if(res==1){
                return userEntity;
            }else {
                return null;
            }
        }
    }


    /**
     * 注销
     * @param strid
     * @return
     */
    public boolean logout(String strid){

        int id=Integer.parseInt(strid);
        int res=userMapper.deleteUser(id);

        //防止重复注销
        UserEntity chekuser=userMapper.selectByid(id);
        if(chekuser==null){
            return true;
        }


        if(id==1){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 查询
     * @param name
     * @return
     */
    public UserEntity select(String name){

        UserEntity userEntity= userMapper.selectByName(name);
        if(userEntity!=null){
            return userEntity;
        }
        else {
            return null;
        }

    }


    public boolean compile(JSONObject jsonObject){


        int id=jsonObject.getInteger("id");
        String name=jsonObject.getString("name");
        String birthday=jsonObject.getString("birthday");
        String gender=jsonObject.getString("gender");


        UserEntity userEntity=new UserEntity();
        userEntity.setId(id);
        userEntity.setName(name);
        userEntity.setGender(gender);
        userEntity.setBirthday(birthday);

        int res=userMapper.updataUser(userEntity);
        if(res==1){
            return true;
        }else {
            return false;
        }



    }
}
