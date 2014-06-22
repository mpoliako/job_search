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
import com.mpoliako.dao.CvDao;
import com.mpoliako.model.CurriculumVitae;

@Transactional
@Component
public class CvDaoImpl implements CvDao{
	
	public static final Logger LOG = Logger.getLogger(CvDaoImpl.class);
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	public List<CurriculumVitae> getAllCv() {
		LOG.debug("getAllCv. Select CurriculumVitae.findAll NamedQuery");
		return em.createNamedQuery("CurriculumVitae.findAll", CurriculumVitae.class).getResultList();		
	}

	public CurriculumVitae getCvById(Integer id) {
		LOG.debug("getCvById. Select CurriculumVitae. Id = " + id);
		return em.find(CurriculumVitae.class, id);	
	}

	public List<CurriculumVitae> getUserCv(String userName) {
		LOG.debug("getUserCv. Select CurriculumVitae.findByUserName NamedQuery, username = " + userName);
		return em.createNamedQuery("CurriculumVitae.findByUserName", CurriculumVitae.class).setParameter(1, userName).getResultList();	
	}

	public void mergeCv(CurriculumVitae cv) {
		LOG.debug("mergeCv. CV = " + cv);
		em.merge(cv);		
	}

	public void removeCv(CurriculumVitae cv) {
		LOG.debug("removeCv. CV = " + cv);
		em.remove(cv);		
	}

	public void removeCv(Integer id) {
		LOG.debug("removeCv. Id = " + id);
		CurriculumVitae entity = em.getReference(CurriculumVitae.class, id);
		em.remove(entity);
	}

	@SuppressWarnings("unchecked")
	public List<CurriculumVitae> getCvByPage(Integer page) {
		LOG.debug("getCvByPage. Select CurriculumVitae.findAll NamedQuery, Page = " + page);
		Query query = em.createNamedQuery("CurriculumVitae.findAll", CurriculumVitae.class);
		query.setFirstResult((page-1)*Constants.RESULTS_PER_PAGE);
		query.setMaxResults(Constants.RESULTS_PER_PAGE);
		return query.getResultList();
	}

	public Integer getCountCv() {
		LOG.debug("getCountCv. Select CurriculumVitae.findAllCount NamedQuery");
		return ((Number)em.createNamedQuery("CurriculumVitae.findAllCount").getSingleResult()).intValue();	      
	}

}
