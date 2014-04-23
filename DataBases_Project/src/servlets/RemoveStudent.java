package servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DBWriter;

/**
 * Servlet implementation adding students
 */
public class RemoveStudent extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		DBWriter writer = new DBWriter();
		
		String studentID = request.getParameter("students");		

		String query = "DELETE FROM Housing WHERE StudentID = " + studentID;
		writer.insertQuery(query);
		
		query = "DELETE FROM ResidentAdvisors WHERE StudentID = " + studentID;
		writer.insertQuery(query);
		
		query = "DELETE FROM ResidentDirectors WHERE StudentID = " + studentID;
		writer.insertQuery(query);
		
		query = "DELETE FROM Students WHERE StudentID = " + studentID;
		writer.insertQuery(query);
			
		try {
			writer.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("message", "Student removed");			
		request.getRequestDispatcher("jsp/removeStudent.jsp").forward(request, response);
		
		
		
	}
}
