package com.Controller;

import com.alibaba.fastjson.JSONObject;
import com.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author ding
 */
@Controller
public class AccountController {

    @Autowired
    AccountService accountService=new AccountService();

    @ResponseBody
    @RequestMapping(value = "/withdrawal")
    public JSONObject withdrawal(@RequestParam("id") String strid,
                                 @RequestParam("money") String strmoney){

        JSONObject jsonObject=new JSONObject();
        if(accountService.withdrawal(strid,strmoney)){

            jsonObject.put("state",1);
            return jsonObject;
        }else {
            jsonObject.put("state",0);
            return jsonObject;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/transferaccount",method = RequestMethod.POST)
    public JSONObject transferaccount(@RequestBody JSONObject data){
        JSONObject jsonObject=new JSONObject();
        if(accountService.transferaccount(data)){
            jsonObject.put("state",1);
            return jsonObject;
        }else {
            jsonObject.put("state",0);
            return jsonObject;
        }

    }

    @ResponseBody
    @RequestMapping(value = "/deposit")
    public JSONObject deposit(@RequestParam("id") String strid,
                              @RequestParam("money") String strmoney){
        JSONObject jsonObject=new JSONObject();
        if(accountService.deposit(strid,strmoney)){
            jsonObject.put("state",1);
            return jsonObject;
        }else {
            jsonObject.put("state",0);
            return jsonObject;
        }
    }
}
