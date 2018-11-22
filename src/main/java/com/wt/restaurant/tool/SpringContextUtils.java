package com.wt.restaurant.tool;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;

/**
 * 实现ApplicationContextAware接口.
 * 该类主要用于获取被spring容器管理的bean.
 * @author Daryl
 */
public class SpringContextUtils implements ApplicationContextAware{

	private static WebApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = (WebApplicationContext) applicationContext;
	}
	
	public static <T> T  getBeanById(String beanId,Class<T> clazz) {
		return context.getBean(beanId, clazz);
	}
	
	public static <T>T getBeanByClass(Class<T> clazz){
		return context.getBean(clazz);
	}
}
