package com.mpoliako.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity(name = "curriculum")
@NamedQueries({
		@NamedQuery(name = "CurriculumVitae.findAll", query = "FROM curriculum"),
		@NamedQuery(name = "CurriculumVitae.findByUserName", query = "FROM curriculum WHERE owner.email =?"),
		@NamedQuery(name ="CurriculumVitae.findAllCount",query = "SELECT count(c) FROM curriculum c")})
public class CurriculumVitae {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Size(min = 1)
	@Column(name = "name")
	private String name;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<JobExperience> expierence;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<JobSkill> skills;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Course> courses;

	@Column(name = "merit")
	private String merit;

	@Pattern(regexp = "[0-9]+.*")
	@Column(name = "salary")
	private String salary;

	@Size(min = 1)
	@Column(name = "area")
	private String area;

	@ManyToOne(cascade = CascadeType.ALL)
	private User owner;

	public CurriculumVitae() {
		super();
	}

	public CurriculumVitae(String name, List<JobExperience> expierence,
			List<JobSkill> skills, List<Course> courses, String merit,
			String salary, String area, User owner) {
		super();
		this.name = name;
		this.expierence = expierence;
		this.skills = skills;
		this.courses = courses;
		this.merit = merit;
		this.salary = salary;
		this.area = area;
		this.owner = owner;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<JobExperience> getExpierence() {
		return expierence;
	}

	public void setExpierence(List<JobExperience> expierence) {
		this.expierence = expierence;
	}

	public List<JobSkill> getSkills() {
		return skills;
	}

	public void setSkills(List<JobSkill> skills) {
		this.skills = skills;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public String getMerit() {
		return merit;
	}

	public void setMerit(String merit) {
		this.merit = merit;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((courses == null) ? 0 : courses.hashCode());
		result = prime * result
				+ ((expierence == null) ? 0 : expierence.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((merit == null) ? 0 : merit.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((salary == null) ? 0 : salary.hashCode());
		result = prime * result + ((skills == null) ? 0 : skills.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurriculumVitae other = (CurriculumVitae) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (courses == null) {
			if (other.courses != null)
				return false;
		} else if (!courses.equals(other.courses))
			return false;
		if (expierence == null) {
			if (other.expierence != null)
				return false;
		} else if (!expierence.equals(other.expierence))
			return false;
		if (merit == null) {
			if (other.merit != null)
				return false;
		} else if (!merit.equals(other.merit))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (salary == null) {
			if (other.salary != null)
				return false;
		} else if (!salary.equals(other.salary))
			return false;
		if (skills == null) {
			if (other.skills != null)
				return false;
		} else if (!skills.equals(other.skills))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CurriculumVitae [id=" + id + ", name=" + name + ", expierence="
				+ expierence + ", skills=" + skills + ", courses=" + courses
				+ ", merit=" + merit + ", salary=" + salary + ", area=" + area
				+ ", owner=" + owner + "]";
	}

}
