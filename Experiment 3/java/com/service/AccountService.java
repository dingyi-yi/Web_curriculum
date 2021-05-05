package com.service;

import com.alibaba.fastjson.JSONObject;
import com.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ding
 */
@Service
public class AccountService {


    @Autowired
    AccountMapper accountMapper;

    /**
     * 取款
     * @return
     */
    public boolean withdrawal(String strid,String strmoney){

        //数据类型转换
        int id=Integer.parseInt(strid);
        float money=Float.parseFloat(strmoney);

        int res=accountMapper.withdrawal(id,money);
        if(res==1){
            return true;
        }else {
            return false;
        }
    }


    /**转账
     * @param jsonObject
     * @return
     */
    public boolean transferaccount(JSONObject jsonObject){
        int selfid=jsonObject.getInteger("selfid");
        int oppositeid=jsonObject.getInteger("oppositeid");
        float money=jsonObject.getFloat("money");

        int resw=accountMapper.withdrawal(selfid,money);
        int resd=accountMapper.deposit(oppositeid,money);
        if(resw==1&&resd==1){
            return true;
        }else{
            return false;
        }


    }

    /**
     * 存款
     * @param strid
     * @param strmoney
     * @return
     */
    public boolean deposit(String strid,String strmoney){
        int id=Integer.parseInt(strid);
        float money=Float.parseFloat(strmoney);

        int res=accountMapper.deposit(id,money);
        if(res==1){
            return true;
        }else {
            return false;
        }
    }
}
