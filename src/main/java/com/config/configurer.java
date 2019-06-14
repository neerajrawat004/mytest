package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com"})
public class configurer extends WebMvcConfigurerAdapter{
	@Bean
	public TilesConfigurer tileConfigurer(){
		TilesConfigurer tileConfigurer= new TilesConfigurer();
		tileConfigurer.setDefinitions(new String[] {"/WEB-INF/views/**/tiles.xml"});
		tileConfigurer.setCheckRefresh(true);
		return tileConfigurer;
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry){
		TilesViewResolver TVR= new TilesViewResolver();
		registry.viewResolver(TVR);
	}
	

}
