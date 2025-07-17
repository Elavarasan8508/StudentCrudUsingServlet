package net.javacode.codee.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO{
	
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	
	public StudentDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	protected void connect() throws SQLException{
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			}catch(ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
		}
		
	}
	protected void disConnect() throws SQLException{
		if(jdbcConnection != null || !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	public boolean insertStudent(Student student) {
	    String sql = "INSERT INTO student(name, age, college) VALUES (?, ?, ?)";
	    boolean rowInserted = false;

	    try {
	        connect();
	        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	        statement.setString(1, student.getName());
	        statement.setInt(2, student.getAge());
	        statement.setString(3, student.getCollege());

	        rowInserted = statement.executeUpdate() > 0;
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    } finally {
	    	try {
	        disConnect();
	    	}catch(SQLException e){
	    		e.printStackTrace();
	    	}
	    }

	    return rowInserted;
	}


	
	public List<Student> listAllStudents() throws SQLException{
		List<Student> listStudent = new ArrayList<>();
		
		String sql = "SELECT * FROM student";
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			String college = rs.getString("college");
			
			Student student = new Student(id,name,age,college);
			listStudent.add(student);
		}
		rs.close();
		statement.close();
		disConnect();
		return listStudent;
 	}
	
	public boolean deleteStudent(Student student) {
	    String sql = "DELETE FROM student WHERE id = ?";
	    boolean rowDeleted = false;

	    try {
	        connect();
	        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
	            statement.setInt(1, student.getId());
	            rowDeleted = statement.executeUpdate() > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            disConnect();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return rowDeleted;
	}

	
	public boolean updateStudent(Student student) {
	    String sql = "UPDATE student SET name = ?, age = ?, college = ? WHERE id = ?";
	    boolean rowUpdated = false;

	    try {
	        connect();
	        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
	            statement.setString(1, student.getName());
	            statement.setInt(2, student.getAge());
	            statement.setString(3, student.getCollege());
	            statement.setInt(4, student.getId());

	            rowUpdated = statement.executeUpdate() > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            disConnect();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return rowUpdated;
	}

	
	public Student getStudent(int id) {
	    Student student = null;
	    String sql = "SELECT * FROM student WHERE id = ?";

	    try {
	        connect();
	        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
	            statement.setInt(1, id);
	            try (ResultSet rs = statement.executeQuery()) {
	                if (rs.next()) {
	                    String name = rs.getString("name");
	                    int age = rs.getInt("age");
	                    String college = rs.getString("college");
	                    
	                    student = new Student(id, name, age, college);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            disConnect();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return student;
	}

}


