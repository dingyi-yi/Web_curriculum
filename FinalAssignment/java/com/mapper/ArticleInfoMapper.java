package com.mapper;

import com.entity.ArticleInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dingxinlong
 * @date 2021年05月03日  14:05
 */
@Mapper
@Repository
public interface ArticleInfoMapper {

    /**
     * 根据用户账号查找
     * @return
     */
    List<ArticleInfoEntity> selectByusId(String usId);


    /**
     * 插入一条信息
     * @param articleInfoEntity
     * @return
     */
    int insertArticleInfo(ArticleInfoEntity articleInfoEntity);

    /**
     * 删除一条文章信息
     * @param arId
     * @return
     */
    int deleteArticleInfo(String arId);

    /**
     * 更新一骗文章信息
     * @param arId
     * @param title
     * @param content
     * @return
     */
    int updataArticleInfo(String arId,String title,String content);

}
