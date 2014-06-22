package com.mpoliako.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
import com.mpoliako.dao.UserDao;
import com.mpoliako.dao.VacancyDao;
import com.mpoliako.model.JobExperience;
import com.mpoliako.model.JobSkill;
import com.mpoliako.model.User;
import com.mpoliako.model.Vacancy;

@Controller
public class VacancyController {

	public static final Logger LOG = Logger.getLogger(VacancyController.class);

	private VacancyDao vacancyDao;
	private UserDao userDao;

	public VacancyController() {
	}

	@Inject
	public VacancyController(VacancyDao vacancyDao, UserDao userDao) {
		this.vacancyDao = vacancyDao;
		this.userDao = userDao;
	}

	@RequestMapping(value = "/vacancy/{id}", method = GET)
	public String showVacancy(
			@RequestHeader(value = "referer", required = false) String referer,
			@PathVariable Integer id, Map<String, Object> model) {

		LOG.info("Prepare to show selected vacancy. Id = " + id);

		model.put("vacancy", vacancyDao.getVacancyById(id));

		if (referer != null && referer.indexOf("vacancy/my") != -1) {
			LOG.info("return selectedVacancyMy view");
			return "selectedVacancyMy";
		} else {
			LOG.info("return selectedVacancy view");
			return "selectedVacancy";
		}

	}

	@RequestMapping(value = { "/vacancy/search", "/" }, method = GET)
	public String searchVacancy(Map<String, Object> model) {
		return searchVacancyPage(1, model);
	}

	@RequestMapping(value = "/vacancy/search/page/{page}", method = GET)
	public String searchVacancyPage(@PathVariable Integer page,
			Map<String, Object> model) {

		LOG.info("Prepare to show " + page + " page of Vacancy list");

		model.put("vacancies", vacancyDao.getVacanciesByPage(page));
		model.put("vacancyCount", vacancyDao.getCountVacancies());
		model.put("resPerPage", Constants.RESULTS_PER_PAGE);

		LOG.debug("Got list of Vacancies from DB: " + model.get("vacancies"));
		LOG.info("return searchVacancy view");

		return "searchVacancy";
	}

	@RolesAllowed("ROLE_AUTHENTIFICATED")
	@RequestMapping(value = "/vacancy/my", method = GET)
	public String myVacancy(Map<String, Object> model) {

		LOG.info("Prepare to show personal Vacancies");

		org.springframework.security.core.userdetails.User user = getUser();
		if (user == null)
			return "searchCv";

		String name = user.getUsername();
		model.put("vacancies", vacancyDao.getUserVacancy(name));

		LOG.debug("Got list of Vacancies from DB: " + model.get("vacancies"));
		LOG.info("return myVacancy view");

		return "myVacancy";
	}

	@RolesAllowed("ROLE_AUTHENTIFICATED")
	@RequestMapping(value = "/vacancy/add", method = GET)
	public String addVacancyForm(Map<String, Object> model) {

		LOG.info("Prepearing add Vacancy form");

		Vacancy vacancy = new Vacancy();
		List<JobExperience> experience = new AutoPopulatingList<JobExperience>(
				JobExperience.class);
		List<JobSkill> skills = new AutoPopulatingList<JobSkill>(JobSkill.class);

		vacancy.setExpierence(experience);
		vacancy.setSkills(skills);

		model.put("vacancy", vacancy);
		LOG.info("return addVacancy view");

		return "addVacancy";
	}

	@RolesAllowed("ROLE_AUTHENTIFICATED")
	@RequestMapping(value = "/vacancy/add", method = POST)
	public String addVacancySubmit(@Valid Vacancy vacancy,
			BindingResult bindingResult) {

		LOG.info("Prepare to add Vacancy, vacancy = " + vacancy);

		if (bindingResult.hasErrors()) {
			LOG.info("Request for add Vacancy is no Valid. Return addVacancy view");
			return "addVacancy";
		}

		if (vacancy.getOwner() == null) {
			org.springframework.security.core.userdetails.User springUser = getUser();

			if (springUser == null) {
				LOG.warn("No authentificated user found. Add Vacancy request cannot be proceed");
				return "redirect:/searchCv";
			}

			User user = userDao.getUser(springUser.getUsername());
			vacancy.setOwner(user);
		}

		vacancyDao.mergeVacancy(vacancy);

		LOG.info("Vacancy added. Redirect to /vacancy/my view");

		return "redirect:/vacancy/my";
	}

	@RequestMapping(value = "/vacancy/edit/{id}", method = GET)
	public String editVacancy(@PathVariable Integer id,
			Map<String, Object> model) {
		LOG.info("Edit " + id + " Vacancy");

		Vacancy vacancy = vacancyDao.getVacancyById(id);

		System.out.println(getUser().getUsername() + " "
				+ vacancy.getOwner().getEmail());

		if (getUser() == null
				|| !getUser().getUsername().equals(
						vacancy.getOwner().getEmail())) {
			return "redirect:/cv/search";
		}

		model.put("vacancy", vacancy);

		LOG.debug("Got Vacancy from DB: " + model.get("vacancy"));
		LOG.info("return addVacancy view");

		return "addVacancy";
	}

	@RequestMapping(value = "/vacancy/delete/{id}", method = GET)
	public String deleteVacancy(@PathVariable Integer id,
			Map<String, Object> model) {
		System.out.println("search " + id);
		
		LOG.info("Delete " + id + " Vacancy");
		Vacancy vacancy = vacancyDao.getVacancyById(id);

		System.out.println(getUser().getUsername() + " "
				+ vacancy.getOwner().getEmail());

		if (getUser() == null
				|| !getUser().getUsername().equals(
						vacancy.getOwner().getEmail())) {
			LOG.warn("User isnot owner of Vacancy, redirect to /cv/search");
			return "redirect:/cv/search";
		}

		vacancyDao.removeVacancy(id);
		
		LOG.info("Vacancy " + id + " deleted. Redirect to /vacancy/my view");
		return "redirect:/vacancy/my";
	}

	private org.springframework.security.core.userdetails.User getUser() {
		return (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
	}
}
