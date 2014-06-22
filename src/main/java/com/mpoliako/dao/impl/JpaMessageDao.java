package com.mpoliako.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mpoliako.dao.MessageDao;
import com.mpoliako.model.Message;

@Repository("messageDao")
@Transactional
@Component
public class JpaMessageDao implements MessageDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public JpaMessageDao(){}

	@SuppressWarnings("unchecked")
	public List<Message> getMessages() {
		return (List<Message>) em.createQuery("from message").getResultList();
	}

	public void addMessage(Message msg) {
		em.persist(msg);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	

}
