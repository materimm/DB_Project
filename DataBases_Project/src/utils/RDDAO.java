package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import ra.ResidentAdvisor;
import rd.ResidentDirector;
import student.Student;

public class RDDAO 
{
	public Vector<ResidentDirector> getRDs()
	{
		Vector<ResidentDirector> rdList = new Vector<ResidentDirector>();
		DBReader reader = new DBReader();
		ResultSet rs = null;
		
		String query = "SELECT * FROM ResidentDirectors";
		rs = reader.executeQuery(query);
		try 
		{
			while(rs.next())
			{
				Student st = new Student();
				ResidentDirector rd = new ResidentDirector();
				rd.setBuildingID(rs.getInt("BuildingID"));
				st.setStudentID(rs.getInt("StudentID"));
				rd.setStudent(st);
				
				rdList.add(rd);
			}
			
			for(int i=0; i<rdList.size(); i++)
			{
				query = "SELECT * FROM Students WHERE StudentID = " + rdList.get(i).getStudent().getStudentID();
				rs = reader.executeQuery(query);
				if(rs.next())
				{
					rdList.get(i).getStudent().setFirstName(rs.getString("FirstName"));
					rdList.get(i).getStudent().setLastName(rs.getString("LastName"));
					rdList.get(i).getStudent().setGradYear(rs.getInt("GradYear"));
					rdList.get(i).getStudent().setMajor(rs.getString("Major"));
				}				
			}
			
			reader.close();
			return rdList;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}			
	}
	
	public void setRD(ResidentDirector rd)
	{
		DBWriter writer = new DBWriter();
		String query = "INSERT INTO ResidentDirectors (StudentID, BuildingID) VALUES ("
				+ rd.getStudent().getStudentID() + ", " + rd.getBuildingID() + ")";
		writer.insertQuery(query);
		
		try {
			writer.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeRD(ResidentDirector rd)
	{
		DBWriter writer = new DBWriter();
		
		String query = "DELETE FROM ResidentDirectors WHERE StudentID = " + rd.getStudent().getStudentID();
		writer.insertQuery(query);
		
		try {
			writer.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
