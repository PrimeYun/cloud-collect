package com.collect.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.collect.common.utils.SpringContextHolder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Bean
	public SpringContextHolder springContextHolder() {
		return new SpringContextHolder();
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		WebMvcConfigurer.super.configureContentNegotiation(configurer);
		configurer.favorPathExtension(false);
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.serializationInclusion(JsonInclude.Include.NON_NULL);
		ObjectMapper mapper = builder.build();
		SimpleModule simpleModule = new SimpleModule();
		// Long -> String
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		mapper.registerModule(simpleModule);
		// 忽略 transient 修饰的属性
		mapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
		converters.add(0, new MappingJackson2HttpMessageConverter(mapper));
		WebMvcConfigurer.super.configureMessageConverters(converters);
	}

}
