package servlets;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.Student;
import utils.StudentDAO;

/**
 * Servlet implementation class courseSearch
 */
//@WebServlet("/course_search")
public class HousingSearch extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Vector<Student> studentList = new Vector<Student>();
		StudentDAO sdao = new StudentDAO();
		
		int buildingID = Integer.parseInt(request.getParameter("build"));
		studentList = sdao.getStudents();
		String names = "";
		
		for(Student st : studentList)
		{
			if(st.getBuildingID() == buildingID)
			{
				names += st.getFirstName() + " " + st.getLastName() + "<br>";
			}
		}		
		
		request.setAttribute("Names", names);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/housing.jsp");
		dispatcher.forward(request, response);
			
	}
}

