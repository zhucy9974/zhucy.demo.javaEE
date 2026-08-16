package demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import demo.service.JwtService;

@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {

	private final JwtService jwtService;

	public CustomWebMvcConfigurer(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Bean
	public CustomInterceptor customInterceptor() {
		return new CustomInterceptor(jwtService);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(customInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns(
						"/auth/login",
						"/error"
				);
	}
}
