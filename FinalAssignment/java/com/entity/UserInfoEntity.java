package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author dingxinlong
 * @date 2021年28月02日  0:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserInfoEntity {
    public String usId;
    public String usName;
    public String usPassword;
    public String birthday;
    public String email;
    private float money;
    private int articleNum;
}
