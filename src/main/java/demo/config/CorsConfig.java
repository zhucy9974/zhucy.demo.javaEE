package demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:4200",
                        "https://zhucy-demo-angular.web.app",
                        "https://zhucy-demo-angular.firebaseapp.com")// 1 设置访问源地址
                .allowCredentials(true)// 2 设置访问源请求头
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS") // 3 设置访问源请求方法
                .maxAge(3600);
    }

}
