package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author dingxinlong
 * @date 2021年02月03日  14:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ArticleInfoEntity {

    public  String usId;
    public  String arId;
    public  String title;
    public String content;
}
