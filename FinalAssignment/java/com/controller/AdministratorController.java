package com.controller;

import com.entity.AdministratorEntity;
import com.service.AdministratorService;
import com.uitls.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author dingxinlong
 * @date 2021年55月30日  14:55
 */
@Controller
public class AdministratorController {

    @Autowired
    AdministratorService administratorService=new AdministratorService();

    /**
     * 管理员登录
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/login" )
    public String login(@RequestParam("adId") String adId,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model){
        AdministratorEntity administratorEntity=administratorService.login(adId,password);
        if(administratorEntity!=null){
            session.setAttribute("administrator",administratorEntity);
            return "redirect:/main.html";
        }else {
            model.addAttribute("alert","账号或者密码错误");
            return "login";
        }
    }



    /**
     * 修改密码
     * @param adId
     * @param newPassword
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changePassword" )
    public AjaxResult changePassword(@RequestParam("adId") String adId,
                                     @RequestParam("newPassword") String newPassword){

        return administratorService.changePassword(adId,newPassword);

    }


    /**
     * 注册
     * @param adName
     * @param adPassword
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/registration" )
    public String registration(@RequestParam("adName") String adName,
                               @RequestParam("adPassword") String adPassword,
                               HttpSession session,
                               Model model){

        AdministratorEntity administratorEntity=administratorService.registration(adName,adPassword);
        if(administratorEntity!=null){
            session.setAttribute("administrator",administratorEntity);
            return "redirect:/main.html";
        }
        model.addAttribute("alert","失败");
        return "registration";

    }


    /**
     * 更新用户信息
     * @param adId
     * @param newName
     * @param newBirthday
     * @param newEmail
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateSelfInfo")
    public AjaxResult updateSelfInfo(@RequestParam("adId") String adId,
                                     @RequestParam("newName") String newName,
                                     @RequestParam("newBirthday") String newBirthday,
                                     @RequestParam("newEmail") String newEmail){
        return administratorService.updateSelfInfo(adId,newName,newBirthday,newEmail);

    }



}
