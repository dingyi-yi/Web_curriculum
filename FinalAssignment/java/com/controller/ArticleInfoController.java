package com.controller;

import com.service.ArticleInfoService;
import com.uitls.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dingxinlong
 * @date 2021年28月03日  14:28
 */
@Controller
public class ArticleInfoController {

    @Autowired
    ArticleInfoService articleInfoService=new ArticleInfoService();

    /**
     * 文章管理首页数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/articleInfoLoad")
    public AjaxResult articleInfoLoad(){
        return articleInfoService.pageLoad();
    }


    @ResponseBody
    @RequestMapping("/userArticleManagement")
    public AjaxResult userArticleMan(@RequestParam("usId") String usId){
        return articleInfoService.userArticle(usId);
    }


    /**
     * 用户新增一篇文章
     * @param usId
     * @param title
     * @param cotent
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertUserArticle")
    public AjaxResult insertUserArticle(@RequestParam("usId") String usId,
                                        @RequestParam("title") String title,
                                        @RequestParam("content") String cotent){
        return articleInfoService.insertUserArticle(usId,title,cotent);
    }

    /**
     * 删除用户一篇文章
     * @param usId
     * @param arId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteUserArticle")
    public AjaxResult deleteUserArticle(@RequestParam("usId") String usId,
                                        @RequestParam("arId") String arId){
    return articleInfoService.deleteUserArticle(usId,arId);
    }


    /**
     * 更新一篇文章
     * @param arId
     * @param title
     * @param cotent
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateUserArticle")
    public AjaxResult updataUserArticle( @RequestParam("arId") String arId,
                                        @RequestParam("title") String title,
                                        @RequestParam("content") String cotent){
        return articleInfoService.updataUserArticle(arId,title,cotent);
    }


}
