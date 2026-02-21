package com.luv2code.jsf.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.luv2code.jsf.entities.Student;

public class StudentDbUtil {
	private static StudentDbUtil instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/student_tracker";

	public static StudentDbUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new StudentDbUtil();
		}
		return instance;
	}

	private StudentDbUtil() throws Exception {
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		DataSource dataSource = (DataSource) context.lookup(jndiName);
		return dataSource;
	}

	private Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	public List<Student> getStudents() throws SQLException {
		List<Student> students = new ArrayList<>();
		try (Connection con = getConnection(); Statement stmt = con.createStatement();) {
			String sql = """
					SELECT * FROM student s
					ORDER by s.last_name
					""";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int    id        = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName  = rs.getString("last_name");
				String email     = rs.getString("email");
			
				Student student = new Student(id,firstName, lastName, email);
				students.add(student);
			}
			rs.close();
		}

		return students;
	}

	public void addStudent(Student student) throws SQLException {
		try (Connection con = getConnection()) {
			String sql = """
					INSERT INTO student(first_name, last_name, email)
					VALUES (?,?,?)
					""";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student.getFirstName());
			ps.setString(2, student.getLastName());
			ps.setString(3, student.getEmail());
			ps.execute();
			ps.close();
		}
		
	}

	public Student getStudent(int id) throws SQLException {
		Student student = null;
		try(Connection con = getConnection()) {
			String sql = """
					SELECT * 
					FROM student s
					WHERE s.id = ?
					""";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName  = rs.getString("last_name");
				String email     = rs.getString("email");
				student = new Student(id, firstName, lastName, email);
			}
			
			ps.close();
			rs.close();
		}	
		return student;
	}

	public void updateStudent(Student student) throws SQLException {
		try(Connection con = getConnection()) {
			String sql = """
					UPDATE student s
					SET s.first_name = ?, s.last_name = ?, s.email = ?
					WHERE s.id = ?
					""";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student.getFirstName());
			ps.setString(2, student.getLastName());
			ps.setString(3, student.getEmail());
			ps.setInt(4, student.getId());
			
			ps.executeUpdate();
			
			ps.close();
		}	
	}

	public void deleteStudent(int id) throws SQLException {
		try(Connection con = getConnection()) {
			String sql = """
					DELETE FROM student s
					WHERE s.id = ?
					""";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
		}
	}

	public List<Student> searchStudent(String searchValue) throws SQLException {
		List<Student> students = new ArrayList<>();
		Student student = null;
		searchValue = "%" + searchValue + "%";
		try(Connection con = getConnection()) {
			String sql = """
					SELECT * 
					FROM student s
					WHERE s.first_name LIKE ?
					OR s.last_name LIKE ? 
					OR s.email LIKE ?
					""";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, searchValue);
			ps.setString(2, searchValue);
			ps.setString(3, searchValue);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id 			 = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName  = rs.getString("last_name");
				String email     = rs.getString("email");
				student = new Student(id, firstName, lastName, email);
				students.add(student);
			}
			
			ps.close();
			rs.close();
		
		}	
		return students;
	}
}
