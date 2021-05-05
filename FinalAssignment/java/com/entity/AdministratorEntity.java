package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author dingxinlong
 * @date 2021年42月30日  20:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AdministratorEntity {
    private String adId;
    private String adName;
    private String adPassword;
    private String birthday;
    private String email;
    private float money;
}
