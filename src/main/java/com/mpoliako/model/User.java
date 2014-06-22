package com.mpoliako.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity(name = "user")
@NamedQuery(name="User.findByUserName", query="from user where email=?")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Size(min = 3, max = 40)
	@Column(name = "login")
	private String login;

	@Size(min = 6, max = 20)
	@Column(name = "password")
	private String password;

	@Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
	@Column(name = "email")
	private String email;

	@Pattern(regexp = "[0-9]+")
	@Column(name = "contact_number")
	private String contactNumber;

	@OneToMany(cascade = CascadeType.ALL)
	private List<CurriculumVitae> cv;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Vacancy> vacancy;

	public User() {
		super();
	}

	public User(String login, String password, String email,
			String contactNumber) {
		super();
		this.login = login;
		this.password = password;
		this.email = email;
		this.contactNumber = contactNumber;
	}

	public User(String login, String password, String email,
			String contactNumber, List<CurriculumVitae> cv) {
		super();
		this.login = login;
		this.password = password;
		this.email = email;
		this.contactNumber = contactNumber;
		this.cv = cv;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public List<CurriculumVitae> getCv() {
		return cv;
	}

	public void setCv(List<CurriculumVitae> cv) {
		this.cv = cv;
	}

	public List<Vacancy> getVacancy() {
		return vacancy;
	}

	public void setVacancy(List<Vacancy> vacancy) {
		this.vacancy = vacancy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contactNumber == null) ? 0 : contactNumber.hashCode());
		result = prime * result + ((cv == null) ? 0 : cv.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
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
		User other = (User) obj;
		if (contactNumber == null) {
			if (other.contactNumber != null)
				return false;
		} else if (!contactNumber.equals(other.contactNumber))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ ", email=" + email + ", contactNumber=" + contactNumber + "]";
	}

}
