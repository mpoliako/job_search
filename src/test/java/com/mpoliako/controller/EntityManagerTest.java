package com.mpoliako.controller;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.AfterClass;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class EntityManagerTest {

	private static XmlWebApplicationContext ctx;
	private static JpaTransactionManager txManager;

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
	public void testEntityManager() {
		EntityManager em = txManager.getEntityManagerFactory().createEntityManager();
		em.close();
	}

	@Test
	public void testSession() {
		Session sesssion = txManager.getEntityManagerFactory().createEntityManager()
				.unwrap(Session.class);
		sesssion.close();
	}
	
	@Test
	public void testConnection() {
		EntityManager em =  txManager.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNativeQuery("SELECT 'mish'");
		assertEquals("mish", query.getSingleResult());
		em.getTransaction().commit();
		em.close();
	}
	
	@AfterClass	
	public static void tearDown() {
		System.out.println("EntityManagerTest test Ended");
	}

}
