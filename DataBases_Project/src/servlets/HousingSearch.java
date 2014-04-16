package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DBReader;

/**
 * Servlet implementation class courseSearch
 */
//@WebServlet("/course_search")
public class HousingSearch extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int buildingID = Integer.parseInt(request.getParameter("build"));
		Vector<Integer> sIDs = new Vector<Integer>();
		Vector<String> firstName = new Vector<String>();
		Vector<String> lastName = new Vector<String>();
		
		//PrintWriter out = response.getWriter();
		DBReader reader = new DBReader();
		ResultSet rs = null;
		try 
		{
			String query = "SELECT StudentID FROM Housing WHERE BuildingID = " + buildingID;
			rs = reader.executeQuery(query);
			
			while(rs.next()){
				sIDs.add(rs.getInt("StudentID"));
			}
			
			for(int i=0; i<sIDs.size(); i++)
			{
				query = "SELECT FirstName, LastName FROM Students WHERE StudentID = " + sIDs.get(i);
				rs = reader.executeQuery(query);
				if(rs.next())
				{
					firstName.add(rs.getString("FirstName"));
					lastName.add(rs.getString("LastName"));
				}
			}
			
			
			request.setAttribute("firstNames", firstName);
			request.setAttribute("lastNames", lastName);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
			reader.close();	
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}

