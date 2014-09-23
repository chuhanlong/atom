package com.chu.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import es.service.JsonUtil;

public abstract class AbstractController implements HandlerInterceptor{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		long startTime = (Long)arg0.getAttribute("startTime");  
		arg0.removeAttribute("startTime");  
        long endTime = System.currentTimeMillis();  
        arg3.addObject("handlingTime",endTime-startTime);  
        String url = arg0.getRequestURL().toString();
        String ip = arg0.getRemoteAddr();
        StringBuilder sb = new StringBuilder("===========>ip:" + ip + ", url:" + url + ", params:");
        if (arg0.getParameterMap() != null) {
            sb.append(JsonUtil.toJson(arg0.getParameterMap()));
        }
        logger.info(sb.toString());
	}

	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		long startTime = System.currentTimeMillis();
		arg0.setAttribute("startTime", startTime);
		return true;
	}

}
