package com.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author ding
 * @des 模板配置
 */
@Configuration
public class ViewResolverConfiguration{
    /**
    @Configuration//用来定义 DispatcherServlet 应用上下文中的 bean
    @EnableWebMvc
    @ComponentScan("com.Controller")//扫描控制器组件
    public class WebConfig extends WebMvcConfigurerAdapter {
        @Bean
        public ViewResolver viewResolver() {
            InternalResourceViewResolver resolver = new InternalResourceViewResolver();
            resolver.setPrefix("/WEB-INF/");
            resolver.setSuffix(".jsp");
            resolver.setViewNames("jsp/*");//当控制器返回的viewName符合规则时才使用这个视图解析器
            resolver.setOrder(2);//设置优先级,数值越小优先级越高
            return resolver;
        }


    }**/
}
