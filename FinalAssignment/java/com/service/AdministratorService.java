package com.service;

import com.entity.AdministratorEntity;
import com.mapper.AdministratorMapper;
import com.uitls.AjaxResult;
import com.uitls.IDCodeUitls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author dingxinlong
 * @date 2021年40月30日  20:40
 */
@Service
public class AdministratorService {

    @Autowired
    AdministratorMapper administratorMapper;

    /**
     * 管理员登录
     * @param adName
     * @param adPassword
     * @return
     */
    public AdministratorEntity login(String adId,String adPassword){

        if(adId.length()==0||adPassword.length()==0){
            return null;
        }

        //查询用户
        AdministratorEntity administratorEntity=administratorMapper.selectByadId(adId);

        if(administratorEntity==null){
            return null;
        }
        //除去密码中空格
        String tempPasswor=administratorEntity.getAdPassword().replace(" ","");
        //检查密码
        if(tempPasswor.equals(adPassword)){
            return administratorEntity;
        }

        return null;


    }


    /**
     * 修改密码
     * @param adId
     * @param newPassword
     * @return
     */
    public AjaxResult changePassword(String adId, String newPassword){

        AjaxResult ajaxResult=new AjaxResult();
        int result=administratorMapper.updataPassword(adId,newPassword);

        if(result!=1){
           ajaxResult.setSuccess(false);
           ajaxResult.setCode(0);
           ajaxResult.setMsg("修改失败");
        }else {
            ajaxResult.setCode(1);
            ajaxResult.setSuccess(true);
            ajaxResult.setMsg("修改成功");
        }
        return ajaxResult;

    }


    /**
     * 管理员注册
     * @param adName
     * @param adPassword
     * @return
     */
    public  AdministratorEntity registration(String adName,String adPassword){

        //姓名密码不能为空
        if(adName.length()<1||adPassword.length()<1){
            return null;
        }

        /*构造用户*/
        //账号
        String adId="AD"+ IDCodeUitls.getUUIDInOrderId();
        //日期
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        AdministratorEntity administratorEntity=new AdministratorEntity(adId,adName,adPassword, df.format(day),
                "123@163.com",0);

        //写入数据库
        int result=administratorMapper.insertAdministrator(administratorEntity);
        if(result!=1){
            return null;
        }
        return administratorEntity;


    }


    /**
     * 更新用户信息
     * @param adId
     * @param newName
     * @param newBirthday
     * @param newEmail
     * @return
     */
    public AjaxResult updateSelfInfo(String adId,String newName,String newBirthday,String newEmail){

        AjaxResult ajaxResult=new AjaxResult();

        //更新数据库
        int updateR= administratorMapper.updateSelfInfo(adId,newName,newBirthday,newEmail);



        if(updateR!=1){
            ajaxResult.setCode(0);
            ajaxResult.setMsg("更新失败");
            return ajaxResult;
        }
        ajaxResult.setCode(1);
        ajaxResult.setMsg("更新成功");

        return ajaxResult;
    }

}
