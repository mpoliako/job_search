package com.mpoliako.model;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class MessageTest {
	
	private static XmlWebApplicationContext ctx;
	private static JpaTransactionManager txManager;
	private String description = "Hello from JPA";

	@BeforeClass
	public static void setUp() {
		ctx = new XmlWebApplicationContext() {
			public String[] getConfigLocations() {
				return new String[] { "file:src/main/webapp/WEB-INF/conf/spring/spring-jdbc.xml" };
			}
		};
		ctx.refresh();
		txManager = (JpaTransactionManager) ctx.getBean("transactionManager");

	}

	@Test
	public void testInsertMessage() {
		EntityManager em = txManager.getEntityManagerFactory().createEntityManager();
		Message message = new Message(description);
		em.getTransaction().begin();
		em.persist(message);
		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void testSelectMessage() {
		EntityManager em = txManager.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("from message");
		Message message = (Message)query.getResultList().get(0);
		assertEquals(description, message.getDescription());
		em.getTransaction().commit();
		em.close();
	}

}
