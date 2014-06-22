package com.mpoliako.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mpoliako.model.Message;

public interface MessageDao {
	
	public List<Message> getMessages();
	
	public void addMessage(Message msg);

}
