package com.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountMapper {

    /**
     * 取款
     * @param id
     * @param money
     * @return
     */
    int withdrawal(int id,float money);

    /**
     * 存款
     * @param id
     * @param money
     * @return
     */
    int deposit(int id,float money);
}
