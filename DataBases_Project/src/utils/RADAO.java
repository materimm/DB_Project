package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import ra.ResidentAdvisor;
import student.Student;

public class RADAO 
{
	
	public Vector<ResidentAdvisor> getRAs()
	{
		Vector<ResidentAdvisor> ras = new Vector<ResidentAdvisor>();
		DBReader reader = new DBReader();
		ResultSet rs = null;
		String query = "";
		
		try 
		{			
			query = "SELECT * FROM ResidentAdvisors ra INNER JOIN Buildings b "
						+ "ON ra.BuildingID = b.BuildingID ORDER BY Name";
			rs = reader.executeQuery(query);
			while(rs.next())
			{
				ResidentAdvisor ra = new ResidentAdvisor();
				Student st = new Student();
				ra.setResAdID(rs.getInt("ResAdID"));
				st.setStudentID(rs.getInt("StudentID"));
				ra.setStudent(st);
				ra.setBuildingID(rs.getInt("BuildingID"));
				ra.setFloorNum(rs.getInt("FloorNumber"));
				ra.setBuildingName(rs.getString("Name"));
				ras.add(ra);
			}
			
			for(int i=0; i<ras.size(); i++)
			{						
				query = "SELECT * FROM Students s INNER JOIN Housing h ON s.StudentID=h.StudentID"
						+ " WHERE s.StudentID = " + ras.get(i).getStudent().getStudentID();
				rs = reader.executeQuery(query);
				if(rs.next())
				{
					ras.get(i).getStudent().setFirstName(rs.getString("FirstName"));
					ras.get(i).getStudent().setLastName(rs.getString("LastName"));
					ras.get(i).getStudent().setGradYear(rs.getInt("GradYear"));
					ras.get(i).getStudent().setMajor(rs.getString("Major"));		
					ras.get(i).setRoomNum(rs.getInt("RoomNumber"));
				}
			}
			
			reader.close();
			
			return ras;
		}
		catch (Exception xe)
		{	
			System.out.println(xe);
			LogUtils.logException(xe);
			return null;
		}
	}
	
	public void setRA(ResidentAdvisor ra){
		DBWriter writer = new DBWriter();
		
		String query = "INSERT INTO ResidentAdvisors (StudentID, BuildingID, FloorNumber) VALUES ("
				+ ra.getStudent().getStudentID() + ", " + ra.getBuildingID() + ", " + ra.getFloorNum() + ")";
		writer.insertQuery(query);
		
		try {
			writer.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeRA(ResidentAdvisor ra)
	{
		DBWriter writer = new DBWriter();
		
		String query = "DELETE FROM ResidentAdvisors WHERE StudentID = " + ra.getStudent().getStudentID();
		writer.insertQuery(query);
		
		try {
			writer.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}