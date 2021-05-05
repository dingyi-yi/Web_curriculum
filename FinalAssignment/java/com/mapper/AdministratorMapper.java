package com.mapper;

import com.entity.AdministratorEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author dingxinlong
 * @date 2021年57月30日  20:57
 */

@Mapper
@Repository
public interface AdministratorMapper {



    /**
     * 通过名字查询
     * @param adName
     * @return
     */
    AdministratorEntity selectByadName(String adName);


    /**
     * 通过账号查询
     * @param adId
     * @return
     */
    AdministratorEntity selectByadId(String adId);



    /**
     * 新增加一个管理员
     * @param administratorEntity
     * @return
     */
    int insertAdministrator(AdministratorEntity administratorEntity);


    /**
     *
     * @param adId
     * @param newPassword
     * @return
     */
    int updataPassword(String adId,String newPassword);


    /**
     * 更新个人信息
     * @param adId
     * @param newName
     * @param newBirthday
     * @param newEmail
     * @return
     */
    int updateSelfInfo(String adId,String newName,String newBirthday,String newEmail);


}
