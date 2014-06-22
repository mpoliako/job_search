package com.mpoliako.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mpoliako.Constants;
import com.mpoliako.dao.VacancyDao;
import com.mpoliako.model.Vacancy;

@Transactional
@Component
public class VacancyDaoImpl implements VacancyDao {
	
	public static final Logger LOG = Logger.getLogger(VacancyDaoImpl.class);

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	public VacancyDaoImpl() {
		super();
	}

	public List<Vacancy> getAllVacancies() {
		LOG.debug("getAllVacancies. Select Vacancy.findAll NamedQuery");
		return em.createNamedQuery("Vacancy.findAll",Vacancy.class).getResultList();		
	}
	
	public Vacancy getVacancyById(Integer id) {
		LOG.debug("getVacancyById. Select Vacancy. Id = " + id);
		return em.find(Vacancy.class, id);		
	}

	public List<Vacancy> getUserVacancy(String userName) {		
		LOG.debug("getUserVacancy. Select Vacancy.findByUserName NamedQuery, username = " + userName);
		return em.createNamedQuery("Vacancy.findByUserName",Vacancy.class).setParameter(1, userName).getResultList();	
	}

	public void mergeVacancy(Vacancy vacancy) {
		LOG.debug("mergeVacancy. Vacancy = " + vacancy);
		em.merge(vacancy);
	}

	public void removeVacancy(Integer id) {
		LOG.debug("removeVacancy. Id = " + id);
		Vacancy entity = em.getReference(Vacancy.class, id);
		em.remove(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Vacancy> getVacanciesByPage(Integer page) {
		LOG.debug("getVacanciesByPage. Select Vacancy.findAll NamedQuery, Page = " + page);
		Query query = em.createNamedQuery("Vacancy.findAll", Vacancy.class);
		query.setFirstResult((page-1)*Constants.RESULTS_PER_PAGE);
		query.setMaxResults(Constants.RESULTS_PER_PAGE);
		return query.getResultList();
	}

	public Integer getCountVacancies() {
		LOG.debug("getCountVacancies. Select Vacancy.findAllCount NamedQuery");
		return ((Number)em.createNamedQuery("Vacancy.findAllCount").getSingleResult()).intValue();	  
	}
	

}
