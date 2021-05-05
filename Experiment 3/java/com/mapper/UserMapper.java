package com.mapper;

import com.Entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ding
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 通过名字查询
     * @param name
     * @return
     */
    UserEntity selectByName(String name);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    UserEntity selectByid(int id);

    /**
     * 添加用户
     * @param userEntity
     * @return
     */
    int insertUser(UserEntity userEntity);


    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteUser(int id);


    /**
     * 更新用户信息
     * @param userEntity
     * @return
     */
    int updataUser(UserEntity userEntity);


}
