package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DBReader;
import utils.DBWriter;

/**
 * Servlet implementation adding students
 */
public class StudentAdd extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		DBReader reader = new DBReader();
		DBWriter writer = new DBWriter();
		ResultSet rs = null;
		
		String firstname = request.getParameter("firstName");
		String lastname = request.getParameter("lastName");
		String gradyear = request.getParameter("gradYear");
		String major = request.getParameter("major");
		
		String res = request.getParameter("Res");
		
		String buildingID = request.getParameter("build");		
		String floornum = request.getParameter("floornum");
		String roomnum = request.getParameter("roomnum");
		
		String sid = "";
		String resID = "";
		
		String query = "";
		
		//Students table insert
		if(firstname.equals("") || firstname == null || lastname.equals("") || lastname == null)
		{
			request.setAttribute("StudentMessage", "Invalid input. Must insert a first and last name.");
			request.getRequestDispatcher("jsp/addStudent.jsp").forward(request, response);
		}
		else
		{
			query = "INSERT INTO Students (FirstName, LastName";
			if(!gradyear.equals("") && gradyear != null)
			{
				query += ", GradYear";
			}
			if(!major.equals("") && major != null)
			{
				query += ", Major";
			}
			
			query += ") VALUES ('" + firstname + "', '" + lastname + "'";
			if(!gradyear.equals("") && gradyear != null)
			{
				query += ", " + gradyear;
			}
			if(!major.equals("") && major != null)
			{
				query += ", '" + major + "'";
			}
			query += ")";	
			
			writer.insertQuery(query);
			request.setAttribute("StudentMessage", "Student Added Successfully");
				
			query = "";
			if(res != null)
			{
				query = "SELECT max(StudentID) FROM Students";
				rs = reader.executeQuery(query);
				try 
				{
					if(rs.next())
					{
						sid = rs.getString("max(StudentID)");														
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				
				//RD table insert
				if(res.equals("RD"))
				{
					System.out.println("RD");
					query = "INSERT INTO ResidentDirectors (StudentID, BuildingID) VALUES (" + sid + ", " + buildingID + ")";	
					writer.insertQuery(query);
				}
				else if(res.equals("RA"))
				{
					System.out.println("RA");
					query = "INSERT INTO ResidentAdvisors (StudentID, BuildingID, FloorNumber) "
							+ "VALUES (" + sid + ", " + buildingID + ", " + floornum + ")";
					writer.insertQuery(query);
				}
				else{
					System.out.println("no RD or RA");
				}
				
				
				request.setAttribute("ResMessage", "RA/RD Added Successfully");
			}
			
			//Housing insert
			query = "";
			if(sid.equals(""))
			{
				query = "SELECT max(StudentID) FROM Students";
				rs = reader.executeQuery(query);
				try 
				{
					if(rs.next())
					{
						sid = rs.getString("max(StudentID)");														
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			
			query = "SELECT ResAdID FROM ResidentAdvisors WHERE BuildingID = " + buildingID + " AND FloorNumber = " + floornum;
			System.out.println(query);
			rs = reader.executeQuery(query);
			try 
			{
				if(rs.next())
				{
					resID = rs.getString("resAdID");														
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
			query = "INSERT INTO Housing (StudentID, BuildingID, FloorNumber, RoomNumber, ResAdID) VALUES (" +
					sid + ", " + buildingID + ", " + floornum + ", " + roomnum + ", " + resID + ")";
			
			writer.insertQuery(query);
			
			try {
				writer.close();
				reader.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			request.setAttribute("HousingMessage", "Housing info Added Successfully");			
			
			request.getRequestDispatcher("jsp/addStudent.jsp").forward(request, response);
			//response.sendRedirect("jsp/addStudent.jsp");
		}
		
		
	}
}
