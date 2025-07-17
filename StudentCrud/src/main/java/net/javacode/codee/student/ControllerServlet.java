package net.javacode.codee.student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        studentDAO = new StudentDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        doGet(req, res);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        String action = req.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(req, res);
                    break;
                case "/insert":
                    insertStudents(req, res);
                    break;
                case "/delete":
                    deleteStudent(req, res);
                    break;
                case "/edit":
                    showEditForm(req, res);
                    break;
                case "/update":
                    updateStudent(req, res);
                    break;
                default:
                    listStudent(req, res);
                    break;
            }
        } catch (Exception e) {
        	    e.printStackTrace(); 
        }
    }

    private void listStudent(HttpServletRequest req, HttpServletResponse res) {
        try {
            List<Student> listStudent = studentDAO.listAllStudents();
            req.setAttribute("studentlist", listStudent);
            RequestDispatcher dispatcher = req.getRequestDispatcher("StudentList.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse res) {
        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher("StudentForm.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse res) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Student existingStudent = studentDAO.getStudent(id);
            req.setAttribute("student", existingStudent);
            RequestDispatcher dispatcher = req.getRequestDispatcher("StudentForm.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertStudents(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
        	
            String name = req.getParameter("name");
            int age = Integer.parseInt(req.getParameter("age"));
            String college = req.getParameter("college");
  
            Student newStudent = new Student(name, age, college);
            studentDAO.insertStudent(newStudent);
            res.sendRedirect("list");
            
        } catch (Exception e) {
            	req.setAttribute("errorMessage", e.getMessage());
        	    RequestDispatcher dispatcher = req.getRequestDispatcher("Error.jsp");
        	    dispatcher.forward(req, res);
        }
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            int age = Integer.parseInt(req.getParameter("age"));
            String college = req.getParameter("college");

            Student student = new Student(id, name, age, college);
            studentDAO.updateStudent(student);
            res.sendRedirect("list");
        } catch (Exception e) {
        	req.setAttribute("errorMessage", e.getMessage());
    	    RequestDispatcher dispatcher = req.getRequestDispatcher("Error.jsp");
    	    dispatcher.forward(req, res);
        }
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse res) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Student student = new Student(id);
            studentDAO.deleteStudent(student);
            res.sendRedirect("list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
