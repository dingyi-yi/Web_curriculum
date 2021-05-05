package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author dingxinlong
 * @date 2021年06月30日  15:06
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("index");
        registry.addViewController("/registration.html").setViewName("registration");
        registry.addViewController("/administrator-info.html").setViewName("administrator-info");
        registry.addViewController("/user-info.html").setViewName("user-info");
        registry.addViewController("/article-index.html").setViewName("article-index");
        registry.addViewController("/article-userArtManage.html").setViewName("article-userArtManage");
    }

    /*
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/css/**","/js/**");
    }*/



}
