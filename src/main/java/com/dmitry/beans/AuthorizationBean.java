package com.dmitry.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.dmitry.dao.AuthorizationDAO;
import com.dmitry.util.SessionUtils;

@ManagedBean
@SessionScoped
public class AuthorizationBean implements Serializable {
	
	private String password;
	private String repeatPassword;
	private String email;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser() {
		return email;
	}

	public void setUser(String email) {
		this.email = email;
	}

	public String getRepeatPassword() { return repeatPassword;}

	public void setRepeatPassword(String repeatPassword) { this.repeatPassword = repeatPassword;}

	//validate login
	public String validateUsernamePassword() {
		boolean valid = AuthorizationDAO.validate(email, password);
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("email", email);
			return "admin";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"This user was not found",
							"Please enter correct login and Password"));
			return "login";
		}
	}

	public String registrationUser() {
		if (!password.equals(repeatPassword)) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Invalid Password",
							"Please enter correct password"));
			return "register";
		}

		boolean insert = AuthorizationDAO.registration(email, password);

		if (insert) {
			return "login";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Invalid Email",
							"Please enter correct email"));
			return "register";
		}
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}
}
