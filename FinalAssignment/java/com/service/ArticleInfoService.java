package com.service;

import com.alibaba.fastjson.JSONObject;
import com.entity.ArticleInfoEntity;
import com.entity.UserInfoEntity;
import com.mapper.ArticleInfoMapper;
import com.mapper.UserInfoMapper;
import com.uitls.AjaxResult;
import com.uitls.IDCodeUitls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dingxinlong
 * @date 2021年30月03日  14:30
 */
@Service
public class ArticleInfoService {


    @Autowired
    ArticleInfoMapper articleInfoMapper;

    @Autowired
    UserInfoMapper userInfoMapper;


    /**
     * 文章管理首页加载数据
     * @return
     */
    public AjaxResult pageLoad(){

        List<UserInfoEntity> userInfoEntityList= userInfoMapper.selectAll();


        //构造用户文章信息表
        List<JSONObject> resultList=new ArrayList<>();

        for(int i=0;i<userInfoEntityList.size();i++){

            UserInfoEntity tempUserInfo= userInfoEntityList.get(i);
            JSONObject tempJson=new JSONObject();
            tempJson.put("usId",tempUserInfo.getUsId());
            tempJson.put("usName",tempUserInfo.getUsName());
            tempJson.put("articleNum",tempUserInfo.getArticleNum());

            resultList.add(tempJson);

        }

        //返回结果
        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setCode(userInfoEntityList.size());
        ajaxResult.setMsg("成功");
        ajaxResult.setData(resultList);

        return ajaxResult;

    }


    /**
     * 获取某个用户的文章信息
     * @param usId
     * @return
     */
    public  AjaxResult userArticle(String usId){

        AjaxResult ajaxResult=new AjaxResult();

        //找到该用户
        UserInfoEntity userInfoEntity=userInfoMapper.selectByusId(usId);

        //找到该用户的所有文章
        List<ArticleInfoEntity> articleInfoEntityList=
                articleInfoMapper.selectByusId(userInfoEntity.getUsId());

        ajaxResult.setCode(articleInfoEntityList.size());
        ajaxResult.setMsg("成功");
        JSONObject resultData=new JSONObject();
        resultData.put("UserInfo",userInfoEntity);
        resultData.put("ArticleList",articleInfoEntityList);
        ajaxResult.setData(resultData);

        return ajaxResult;

    }


    /**
     * 新增一篇文章
     * @param usId
     * @param title
     * @param content
     * @return
     */
    public AjaxResult insertUserArticle(String usId,String title,String content){
        AjaxResult ajaxResult=new AjaxResult();

        if(title.length()<1){
            ajaxResult.setCode(0);
            ajaxResult.setMsg("文章标题必填");
            return ajaxResult;
        }

        /*构造文章实体*/
        String arId="AR"+ IDCodeUitls.getUUIDInOrderId();
        ArticleInfoEntity articleInfoEntity=new ArticleInfoEntity(usId,arId,title,content);

        /*写入/更新数据库*/
        articleInfoMapper.insertArticleInfo(articleInfoEntity);
        userInfoMapper.updataUserArtNum(usId,1);

        ajaxResult.setCode(1);
        ajaxResult.setMsg("添加成功");
        ajaxResult.setData(articleInfoEntity);
        return ajaxResult;

    }

    /**
     * 删除一篇文章
     * @param usId
     * @param arId
     * @return
     */
    public AjaxResult deleteUserArticle(String usId,String arId){
        AjaxResult ajaxResult=new AjaxResult();

        /*更新数据库*/
        int artR=articleInfoMapper.deleteArticleInfo(arId);
        int usrR=userInfoMapper.updataUserArtNum(usId,-1);

        ajaxResult.setCode(1);
        ajaxResult.setMsg("成功");
        return ajaxResult;

    }


    /**
     * 更新一篇文章信息
     * @param arId
     * @param title
     * @param content
     * @return
     */
    public AjaxResult updataUserArticle(String arId,String title,String content){
        AjaxResult ajaxResult=new AjaxResult();

        /*更新数据库*/
        articleInfoMapper.updataArticleInfo(arId,title,content);

        ajaxResult.setCode(1);
        ajaxResult.setMsg("更新成功");
        return ajaxResult;
    }
}
