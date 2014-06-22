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

public class CurriculumVitaeTest {
	
	private static XmlWebApplicationContext ctx;
	private static JpaTransactionManager txManager;
	private static CurriculumVitae cv;

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
	public void testInsertCV() {
		EntityManager em = txManager.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		
		User user = new User("mpoliako", "dgj555mm", "mihapolyakov.ts73@gmail.com", "380932949272");
		List<JobExperience> expierence = Arrays.asList(new JobExperience(1 , "operator"), new JobExperience(1 , "java developer"));
		List<JobSkill> skills = Arrays.asList(new JobSkill("javaCore"), new JobSkill("Spring"), new JobSkill("JPA"));
		List<Course> courses = Arrays.asList(new Course("EpamSystems" , 2));
		
		cv = new CurriculumVitae("Java Developer", expierence, skills, courses, "resopnsible,accurate", "1000 USD", "IT", user);
		user.setCv(Arrays.asList(cv));
		
		em.merge(cv);
		em.getTransaction().commit();
		em.close();
	}
	
	
	@Test
	public void testSelectCV() {
		EntityManager em = txManager.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();	
		
		CurriculumVitae selected = em.find(CurriculumVitae.class, 2);
		
		assertEquals(cv, selected);
		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void testMergeCV() {
		
		EntityManager em = txManager.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();	
		
		CurriculumVitae cv = em.find(CurriculumVitae.class, 2);
		cv.setSalary("1500 usd");
		em.merge(cv);
		em.getTransaction().commit();
		
		
		em.getTransaction().begin();
		CurriculumVitae selected = em.find(CurriculumVitae.class, 2);
		assertEquals(cv, selected);
		em.getTransaction().commit();
		
		
		em.close();
		
	}

}
