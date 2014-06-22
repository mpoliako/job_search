package com.mpoliako.dao;

import java.util.List;

import com.mpoliako.model.Vacancy;

public interface VacancyDao {
	
	public List<Vacancy> getAllVacancies();
	public List<Vacancy> getVacanciesByPage(Integer page);
	public Integer getCountVacancies();
	public Vacancy getVacancyById(Integer id);
	public List<Vacancy> getUserVacancy(String userName);
	public void mergeVacancy(Vacancy vacancy);
	public void removeVacancy(Integer id);
}
