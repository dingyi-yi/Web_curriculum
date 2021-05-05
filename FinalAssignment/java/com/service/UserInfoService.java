package com.service;

import com.alibaba.fastjson.JSONObject;
import com.entity.UserInfoEntity;
import com.mapper.UserInfoMapper;
import com.uitls.AjaxResult;
import com.uitls.IDCodeUitls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dingxinlong
 * @date 2021年27月02日  0:27
 */
@Service
public class UserInfoService {


    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 查询所有用户
     * @return
     */
    public AjaxResult selectAllUser(){

        AjaxResult ajaxResult=new AjaxResult();

        //查数据库
        List<UserInfoEntity> userInfoEntityList= userInfoMapper.selectAll();
        if(userInfoEntityList.isEmpty()){
            ajaxResult.setCode(0);
            ajaxResult.setMsg("暂无数据");
            return ajaxResult;
        }


        ajaxResult.setCode(userInfoEntityList.size());
        ajaxResult.setMsg("查询成功");
        ajaxResult.setData(userInfoEntityList);
        return ajaxResult;
    }

    /**
     * 新增用户信息
     * @param usName
     * @param usPassword
     * @param birthday
     * @param email
     * @return
     */
    public AjaxResult insertUserInfo(String usName,String usPassword,String birthday,String email){

        AjaxResult ajaxResult=new AjaxResult();

        if(usName.length()<1||usPassword.length()<1){
            ajaxResult.setSuccess(false);
            ajaxResult.setCode(0);
            ajaxResult.setMsg("信息不全");
            return ajaxResult;
        }
        /*构造用户信息*/
        //账号
        String usId="US"+ IDCodeUitls.getUUIDInOrderId();
        UserInfoEntity userInfoEntity=new UserInfoEntity(usId,usName,usPassword,birthday,
                                                         email,0,0);

        //写入数据库
        int result=userInfoMapper.insertUserInfo(userInfoEntity);
        if(result!=1){
            ajaxResult.setCode(0);
            ajaxResult.setMsg("写入数据库失败");
            return ajaxResult;
        }

        ajaxResult.setCode(1);
        ajaxResult.setMsg("添加成功");
        ajaxResult.setData(userInfoEntity);
        return ajaxResult;

    }


    /**
     * 删除用户信息
     * @param usId
     * @return
     */
    public AjaxResult deleteUserInfo(String usId){

        AjaxResult ajaxResult=new AjaxResult();

        int result=userInfoMapper.deleteUserInfo(usId);
        if(result!=1){
            ajaxResult.setCode(0);
            ajaxResult.setMsg("删除失败");
            return ajaxResult;
        }
        ajaxResult.setMsg("删除成功");
        ajaxResult.setCode(1);
        return ajaxResult;
    }



    public AjaxResult updataUserInfo(String usId,String usName,String usPassword,String birthday,
                                     String email,String money){
        AjaxResult ajaxResult=new AjaxResult();

        Float moneyf=Float.parseFloat(money);

        UserInfoEntity userInfoEntity=new UserInfoEntity(usId,usName,usPassword,birthday,email,moneyf,0);

        int result=userInfoMapper.updataUserInfo(userInfoEntity);

        if(result!=1){
            ajaxResult.setCode(0);
            ajaxResult.setMsg("更新失败");
            return ajaxResult;
        }
        ajaxResult.setCode(1);
        ajaxResult.setMsg("更新成功");
        return  ajaxResult;

    }


}
