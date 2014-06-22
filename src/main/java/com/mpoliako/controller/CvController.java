package com.mpoliako.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mpoliako.Constants;
import com.mpoliako.dao.CvDao;
import com.mpoliako.dao.UserDao;
import com.mpoliako.model.Course;
import com.mpoliako.model.CurriculumVitae;
import com.mpoliako.model.JobExperience;
import com.mpoliako.model.JobSkill;
import com.mpoliako.model.User;

@Controller
public class CvController {
	
	public static final Logger LOG = Logger.getLogger(CvController.class);

	private CvDao cvDao;
	private UserDao userDao;

	public CvController() {
	}

	@Inject
	public CvController(CvDao cvDao, UserDao userDao) {
		this.cvDao = cvDao;
		this.userDao = userDao;
	}

	@RolesAllowed("ROLE_AUTHENTIFICATED")
	@RequestMapping(value="/cv/my", method = GET)
	public String showMyCv(Map<String, Object> model) {
		
		LOG.info("Prepare to show personal CV's");

		org.springframework.security.core.userdetails.User user = getUser();
		if (user == null)
			return "searchVacancy";

		String name = user.getUsername();

		model.put("myCv", cvDao.getUserCv(name));
		
		LOG.debug("Got list of CV's from DB: " + model.get("myCv"));
		LOG.info("return myCv view");
		
		return "myCv";
	}

	@RequestMapping(value="/cv/search", method = GET)
	public String searchCv(Map<String, Object> model) {
		return searchCvPage(1, model);
	}
	
	@RequestMapping(value="/cv/search/page/{page}", method = GET)
	public String searchCvPage(@PathVariable Integer page, Map<String, Object> model) {
		
		LOG.info("Prepare to show " + page + " page of CV list");
		
		model.put("allCv", cvDao.getCvByPage(page));
		model.put("cvCount", cvDao.getCountCv());
		model.put("resPerPage", Constants.RESULTS_PER_PAGE);
		
		LOG.debug("Got list of CV's from DB: " + model.get("allCv"));
		LOG.info("return searchCv view");
		
		return "searchCv";
	}

	@RequestMapping(value="/cv/{id}", method = GET)
	public String showCv(
			@RequestHeader(value = "referer", required = false) String referer,
			@PathVariable Integer id, Map<String, Object> model) {
		
		LOG.info("Prepare to show " + id + " CV");
		
		model.put("cv", cvDao.getCvById(id));
		
		LOG.debug("Got CV from DB: " + model.get("cv"));

		if (referer != null && referer.indexOf("cv/my") != -1) {
			LOG.info("return selectedCvMy view");
			return "selectedCvMy";
		} else {
			LOG.info("return selectedCv view");
			return "selectedCv";
		}
	}

	@RequestMapping(value="/cv/edit/{id}", method = GET)
	public String editCv(@PathVariable Integer id, Map<String, Object> model) {
		
		LOG.info("Edit " + id + " CV");
		CurriculumVitae cv = cvDao.getCvById(id);

		if (getUser() == null
				|| !getUser().getUsername().equals(cv.getOwner().getEmail())) {
			LOG.warn("User isnot owner of CV, redirect to /vacancy/search");
			return "redirect:/vacancy/search";
		}

		model.put("curriculumVitae", cv);
		LOG.debug("Got CV from DB: " + model.get("curriculumVitae"));
		
		LOG.info("return addCv view");
		return "addCv";
	}

	@RequestMapping(value="/cv/delete/{id}", method = GET)
	public String deleteCv(@PathVariable Integer id, Map<String, Object> model) {

		LOG.info("Delete " + id + " CV");
		
		CurriculumVitae cv = cvDao.getCvById(id);
		
		if (getUser() == null
				|| !getUser().getUsername().equals(cv.getOwner().getEmail())) {
			LOG.warn("User isnot owner of CV, redirect to /vacancy/search");
			return "redirect:/vacancy/search";
		}
		
		cvDao.removeCv(id);
		
		LOG.info("CV " + id + " deleted. Redirect to /cv/my view");
		return "redirect:/cv/my";
	}

	@RolesAllowed("ROLE_AUTHENTIFICATED")
	@RequestMapping(value = "/cv/add", method = GET)
	public String addCvForm(Map<String, Object> model) {

		LOG.info("Prepearing add CV form");
		
		CurriculumVitae cv = new CurriculumVitae();
		List<JobExperience> experience = new AutoPopulatingList<JobExperience>(
				JobExperience.class);
		List<JobSkill> skills = new AutoPopulatingList<JobSkill>(JobSkill.class);
		List<Course> courses = new AutoPopulatingList<Course>(Course.class);

		cv.setExpierence(experience);
		cv.setSkills(skills);
		cv.setCourses(courses);

		model.put("curriculumVitae", cv);
		
		LOG.info("return addCv view");
		return "addCv";
	}

	@RolesAllowed("ROLE_AUTHENTIFICATED")
	@RequestMapping(value = "/cv/add", method = POST)
	public String addCvSubmit(@Valid CurriculumVitae cv,
			BindingResult bindingResult) {

		LOG.info("Prepare to addCV, cv = " + cv);

		if (bindingResult.hasErrors()) {
			LOG.info("Request for add CV is no Valid. Return addCv view");
			return "addCv";
		}

		if (cv.getOwner() == null) {
			org.springframework.security.core.userdetails.User springUser = getUser();

			if (springUser == null) {
				LOG.warn("No authentificated user found. Add CV request cannot be proceed");
				return "redirect:/searchVacancy";
			}	

			User user = userDao.getUser(springUser.getUsername());
			cv.setOwner(user);
		}

		cvDao.mergeCv(cv);
		
		LOG.info("CV added. Redirect to /cv/my view");

		return "redirect:/cv/my";
	}

	private org.springframework.security.core.userdetails.User getUser() {
		return (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
	}

}
