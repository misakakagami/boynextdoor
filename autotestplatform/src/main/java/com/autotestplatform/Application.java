package com.autotestplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.autotestplatform.contants.Contants;
import com.autotestplatform.interceptor.UserInterceptor;

@SpringBootApplication
@ServletComponentScan
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * @Description：设置session过期时间
     * @return EmbeddedServletContainerCustomizer
     * @throws
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.setSessionTimeout(600);
            }
        };
    }

    /**
     * 配置拦截器
     * @author : 孔德华
     * @date : 2018年5月9日 下午5:11:16  
     * @version : 2018年5月9日  创建内部类
     */
    @Component
    @Configuration
    static class WebConfiguere extends WebMvcConfigurerAdapter {
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new UserInterceptor()).addPathPatterns("/**").excludePathPatterns("/", "/login",
                    Contants.NEEDLOGINURL, "/cu", "/createUser");
        }

        public void addResourceHandler(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
            super.addResourceHandlers(registry);
        }
    }
}
