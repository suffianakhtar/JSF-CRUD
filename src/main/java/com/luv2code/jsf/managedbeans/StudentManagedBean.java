package com.luv2code.jsf.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.luv2code.jsf.entities.Student;
import com.luv2code.jsf.jdbc.StudentDbUtil;
import com.luv2code.jsf.wrappers.StudentWrapper;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class StudentManagedBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<Student> students;
	private StudentDbUtil studentDbUtil;
	@Inject
	private StudentWrapper wrapper;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public StudentManagedBean() throws Exception {
		students = new ArrayList<>();
		studentDbUtil = StudentDbUtil.getInstance();
	}
	
	public void loadStudents() {
		logger.info("Loading students");
		students.clear();
		try {
			if (wrapper.getSearchValue() != null && !wrapper.getSearchValue().strip().isEmpty()) {
				students = studentDbUtil.searchStudent(wrapper.getSearchValue());
			} else {
				students = studentDbUtil.getStudents();
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error loading students", e);
			addErrorMessage(e);
		}
	}
	
	public String loadStudent(int id) {
		logger.info("loading student with id:" + id);
		try {
			Student student = studentDbUtil.getStudent(id);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			sessionMap.put("student", student);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error loadin student with id: " + id, e);
			addErrorMessage(e);
		}
		return "update-student-form.xhtml";
	}
	
	public void addStudent() {
		Student student = new Student(0, wrapper.getFirstName(), wrapper.getLastName(), wrapper.getEmail());
		wrapper.setFirstName("");
		wrapper.setLastName("");
		wrapper.setEmail("");
		logger.info("Adding student: " + student);
		try {
			studentDbUtil.addStudent(student);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error adding student", e);
			addErrorMessage(e);
		}
	}
	
	public String updateStudent(Student student) {
		try {
			studentDbUtil.updateStudent(student);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error updating student with id: " + student.getId(), e);
			addErrorMessage(e);
			return null;
		}
		return "list-students?faces-redirect=true";
	}
	
	public void deleteStudent(int id) {
		try {
			studentDbUtil.deleteStudent(id);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error deleting student with id: " + id, e);
			addErrorMessage(e);
		}
	}
	
	private void addErrorMessage(Exception e) {
		FacesMessage message = new FacesMessage("Error: " + e.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public List<Student> getStudents() {
		return students;	
	}
}
