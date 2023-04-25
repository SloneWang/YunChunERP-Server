package com.huang.config;

import com.huang.config.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( jwtInterceptor())
                .addPathPatterns("/**") //拦截所有请求，通过判断token是否合法来决定是否要登录
                .excludePathPatterns("/login","/register","**/export","**/import", "/test")
                .excludePathPatterns("/swagger**/**", "/webjars/**", "/v3/**", "/doc.html");
        //WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/swagger-ui/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
        //WebMvcConfigurer.super.addResourceHandlers(registry);
    }


    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }
}
