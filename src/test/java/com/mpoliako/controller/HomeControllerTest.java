package com.mpoliako.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;

import com.mpoliako.dao.MessageDao;
import com.mpoliako.model.Message;


public class HomeControllerTest {
	
	private String description = "Hello from JPA";
	
	@Test
	public void testShowHomePage() {
		
		MessageDao msgDao = mock(MessageDao.class);
		when(msgDao.getMessages()).thenReturn(Arrays.asList(new Message(description)));
		
		HomeController controller = new HomeController(msgDao);
		HashMap<String, Object> model = new HashMap<String, Object>();
		
		String viewName = controller.showHomePage(model);
		assertEquals("home", viewName);
		assertEquals(description, model.get("firstMsg"));

	}
		

}
