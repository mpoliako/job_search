package com.mpoliako.model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class VacancyTest {
	
	private static XmlWebApplicationContext ctx;
	private static JpaTransactionManager txManager;
	private static Vacancy vacancy;
	private static String description = "Google's software engineers develop the next-generation technologies that change how millions of users connect, explore, and interact with information and one another. Our ambitions reach far beyond just Search. Our products need to handle information at the the scale of the web. We're looking for ideas from every area of computer science, including information retrieval, artificial intelligence, natural language processing, distributed computing, large-scale system design, networking, security, data compression, and user interface design; the list goes on and is growing every day. As a software engineer, you work on a small team and can switch teams and projects as our fast-paced business grows and evolves. We need our engineers to be versatile and passionate to tackle new problems as we continue to push technology forward.";

	@BeforeClass
	public static void setUp() {
		ctx = new XmlWebApplicationContext() {
			public String[] getConfigLocations() {
				return new String[] { "file:src/main/webapp/WEB-INF/conf/spring/spring-jdbc.xml" };
			}
		};
		ctx.refresh();
		txManager = (JpaTransactionManager) ctx.getBean("transactionManager");
		
		User user = new User("Google", "dgj555mm", "google@gmail.com", "380932949272");
		List<JobExperience> expierence = Arrays.asList(new JobExperience(3 , "java developer"));
		List<JobSkill> skills = Arrays.asList(new JobSkill("javaCore"), new JobSkill("Spring"), new JobSkill("JPA"), new JobSkill("GWT"));
		
		vacancy = new Vacancy("Java Developer", expierence, skills, "2500 USD", "IT", user, description);
		user.setVacancy(Arrays.asList(vacancy));

	}
	
	@Ignore
	@Test
	public void testDelete() {
		EntityManager em = txManager.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();	
		em.createQuery("delete from vacancy").executeUpdate();
		em.close();
	}
	
	
	@Test
	public void testInsertVacancy() {
		EntityManager em = txManager.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();	
		em.merge(vacancy);
		em.getTransaction().commit();
		em.close();
	}
	
	
	@Test
	public void testSelectVacancy() {
		EntityManager em = txManager.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();	
		
		Vacancy selected = em.find(Vacancy.class, 2);
		
		assertEquals(vacancy, selected);
		em.getTransaction().commit();
		em.close();
	}
	
	
	@Test
	public void testMergeVacancy() {
		
		EntityManager em = txManager.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();	
		
		Vacancy vacancy = em.find(Vacancy.class, 3);
		vacancy.setSalary("1500 usd");
		em.merge(vacancy);
		em.getTransaction().commit();
		
		
		em.getTransaction().begin();
		Vacancy selected = em.find(Vacancy.class, 3);
		assertEquals(vacancy, selected);
		em.getTransaction().commit();
		
		
		em.close();
		
	}

}
