package com.Controller;

import com.Entity.UserEntity;
import com.service.UserService;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpSession;

/**
 * @author ding
 */
@Controller
public class LoginController {


    @Autowired
    UserService userService=new UserService();

    /**
     * 登录
     * @param name
     * @param password
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "login" )
    public String login(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model){

        UserEntity userEntity=userService.loginService(name,password);

        if(userEntity!=null){
            //返回主页面
            session.setAttribute("user",userEntity);
            return "main";
        }else {
            //返回登录页面，提示账号密码错误。
            model.addAttribute("alert","账号密码错误");
            return "login";
        }
    }


    /**
     * 注册
     * @param name
     * @param password
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "register" )
    public String register(@RequestParam("name") String name,
                           @RequestParam("password") String password,
                           HttpSession session,
                           Model model){
        UserEntity userEntity=userService.register(name,password);

        if(userEntity!=null){
            //返回主页面
            session.setAttribute("user",userEntity);
            return "main";
        }else {
            //返回登录页面，提示账号密码错误。
            model.addAttribute("alert","注册失败");
            return "register";
        }
    }


    /**
     * 注销
     * @param id
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public String logout(@RequestParam("id") String id,
                         HttpSession session,
                         Model model){
        if(userService.logout(id)){
            return "true";
        }else {
            return "false";
        }
    }


    /**
     * 查询
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/select")
    public JSONObject select(@RequestParam("name") String name,
                            HttpSession session){

        UserEntity userEntity=userService.select(name);
        JSONObject resutl=new JSONObject();
        if(userEntity!=null){
            resutl.put("state",1);
            resutl.put("user",userEntity);
            return resutl;
        }else {
            resutl.put("state",0);
            return resutl;
        }

    }


    /**
     * 修改个人信息
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/compile",method = RequestMethod.POST)
    public JSONObject compile(@RequestBody JSONObject data,HttpSession session){

        boolean res=userService.compile(data);
        JSONObject jsonObject=new JSONObject();
        if(res){
            jsonObject.put("state",1);
            return jsonObject;
        }else {
            jsonObject.put("state",0);
            return jsonObject;
        }
    }







    @RequestMapping("/jsp")
    public String b(){
        //返回 /WEB-INF/jsp/index.jsp 页面(jsp)
        return "jsp/index";
    }
}
