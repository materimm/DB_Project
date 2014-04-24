package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.Student;
import utils.StudentDAO;

/**
 * Servlet implementation adding students
 */
public class RemoveStudent extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		StudentDAO sdao = new StudentDAO();
		Student s = new Student();
		s.setStudentID(Integer.parseInt(request.getParameter("students")));
		sdao.removeStudent(s);
	}
}
