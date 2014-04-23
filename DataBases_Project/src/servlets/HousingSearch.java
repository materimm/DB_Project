package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
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
		Vector<String> name = new Vector<String>();
		
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
					String f = rs.getString("FirstName");
					String l = rs.getString("LastName");
					String t = f + " " + l;
					name.add(t);
				}
			}
			
			/*request.getSession().removeAttribute("errorMessage");
            request.getRequestDispatcher("jsp/housing.jsp").forward(request, response);
            request.getSession().setAttribute("firstnames", "test");
            response.sendRedirect(request.getHeader("Referer"));*/
			
			request.setAttribute("firstNames", name);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/housing.jsp");
			dispatcher.forward(request, response);
			
			/*request.setAttribute("firstNames", firstName);
			request.setAttribute("lastNames", lastName);
			request.getRequestDispatcher("jsp/housing.jsp").forward(request, response);*/
			
			reader.close();	
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}

