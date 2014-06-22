package com.mpoliako.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

public class ApplicationListener extends ContextLoaderListener{

	public void contextInitialized(ServletContextEvent ev) {		
		super.contextInitialized(ev);		
	}

}
