package com.mpoliako.dao;

import java.util.List;

import com.mpoliako.model.CurriculumVitae;

public interface CvDao {
	
	public List<CurriculumVitae> getAllCv();
	public Integer getCountCv();
	public List<CurriculumVitae> getCvByPage(Integer page);
	public CurriculumVitae getCvById(Integer id);
	public List<CurriculumVitae> getUserCv(String userName);
	public void mergeCv(CurriculumVitae cv);
	public void removeCv(CurriculumVitae cv);
	public void removeCv(Integer id);

}
