package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.service.UserInfoService;
import com.uitls.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author dingxinlong
 * @date 2021年31月01日  18:31
 */
@Controller
public class UserInfoController {


    @Autowired
    UserInfoService userInfoService=new UserInfoService();


    /**
     * 查询所有数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/pageLoad")
    public AjaxResult pageLoad(){
        return userInfoService.selectAllUser();
    }


    /**
     * 新增用户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertions")
    public AjaxResult insertUserInfo(@RequestParam("usName") String adId,
                                     @RequestParam("usPassword") String usPassword,
                                     @RequestParam("birthday") String birthday,
                                     @RequestParam("email") String email){
        return userInfoService.insertUserInfo(adId,usPassword,birthday,email);
    }


    /**
     * 删除用户信息
     * @param usId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteuserinfo")
    public AjaxResult deleteUserInfo(@RequestParam("usId") String usId){

        return userInfoService.deleteUserInfo(usId);

    }


    /**
     * 更新用户信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/updataUserInfo")
    public AjaxResult updataUserInfo(@RequestParam("usId") String usId,
                                     @RequestParam("usName") String usName,
                                     @RequestParam("usPassword") String usPassword,
                                     @RequestParam("birthday") String birthday,
                                     @RequestParam("email") String email,
                                     @RequestParam("money") String money){
        System.out.println("1");
        return userInfoService.updataUserInfo(usId,usName,usPassword,birthday,email,money);
    }


}
