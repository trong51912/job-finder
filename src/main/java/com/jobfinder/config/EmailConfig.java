package com.jobfinder.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
@Configuration
public class EmailConfig {
	@Bean 
	public JavaMailSender getMailSender() {
	    JavaMailSenderImpl sender = new JavaMailSenderImpl();
	    sender.setHost("smtp.gmail.com");
	    sender.setPort(587);
	    sender.setUsername("Nguyenlocit4.0@gmail.com");
	    sender.setPassword("jikx ulwn zxjf mund");
	    
	    Properties props = sender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true"); 
	    props.put("mail.debug", "true");
	    props.put("mail.smtp.auth.mechanisms", "LOGIN");
	    sender.setJavaMailProperties(props);
	    return sender;
	}
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() 
	{
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(20848820);
	    return multipartResolver;
	}
	@Bean 
	ViewResolver viewResolver() {
	  InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	  resolver.setPrefix("/WEB-INF/views/");
	  resolver.setSuffix(".jsp");
	  return resolver;
	}
	@Bean
    public FreeMarkerConfigurationFactoryBean freeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean factoryBean = new FreeMarkerConfigurationFactoryBean();
        factoryBean.setDefaultEncoding("UTF-8");
        factoryBean.setTemplateLoaderPath("/WEB-INF/views/"); // Set your template loader path
        return factoryBean;
    }

}
