package com.mapper;

import com.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dingxinlong
 * @date 2021年35月02日  0:35
 */
@Mapper
@Repository
public interface UserInfoMapper {

    /**
     * 查询所有用户信息
     * @return
     */
    List<UserInfoEntity> selectAll();


    /**
     * 新增用户信息
     * @param userInfoEntity
     * @return
     */
    int insertUserInfo(UserInfoEntity userInfoEntity);

    /**
     * 删除用户信息
     * @param usId
     * @return
     */
    int deleteUserInfo(String usId);


    /**
     * 更新用户信息
     * @param userInfoEntity
     * @return
     */
    int updataUserInfo(UserInfoEntity userInfoEntity);


    /**
     * 根据用户id查询
     * @param usId
     * @return
     */
    UserInfoEntity selectByusId(String usId);


    /**
     * 更新用户文章数
     * @param usId
     * @param num
     * @return
     */
    int updataUserArtNum(String usId,int num);
}
