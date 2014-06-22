package com.mpoliako.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mpoliako.dao.UserDao;
import com.mpoliako.model.User;


@Transactional
@Component
public class UserDaoImpl implements UserDao {
	
	public static final Logger LOG = Logger.getLogger(UserDaoImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	public void addUser(User user) {
		LOG.debug("addUser. user = " + user);
		em.persist(user);
		em.flush();
	}

	public User getUser(String userName) {	
		try {
			LOG.debug("getUser. Select User.findByUserName NamedQuery, username = " + userName);
			User user = em.createNamedQuery("User.findByUserName", User.class).setParameter(1, userName).getSingleResult();	
			return user;
		} catch (NoResultException nre) {
			LOG.debug("No user found in DB. Return null");
			return null;
		}	
	}

}
