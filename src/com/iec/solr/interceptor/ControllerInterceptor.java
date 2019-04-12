package com.iec.solr.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



/**
 * @Description:拦截器
 * @Author berchina
 * @CreateDate:2012-01-10
 * @version:v1.0
 * 
 */
public class ControllerInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger log = LogManager.getLogger(ControllerInterceptor.class);
	
	
    /**
     * 在Controller方法前进行拦截
     */
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
    	log.info("拦截...");
    	return true;
    }
    
    /**
     * This implementation is empty.
     */
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    /**
     * 在Controller方法后进行拦截
     */
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
