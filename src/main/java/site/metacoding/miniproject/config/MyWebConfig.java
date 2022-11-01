package site.metacoding.miniproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebConfig implements WebMvcConfigurer{
	
	public void addResourceHandlers(ResourceHandlerRegistry resgistry) {
		resgistry.addResourceHandler("/img/**").addResourceLocations("file:///C:/temp/img/");
		//resgistry.addResourceHandler("/img/**").addResourceLocations("file:/Users/ihyeonseong/Desktop/img/");// Mac전용 경
		
	}
}
