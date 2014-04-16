package utils;

import java.sql.ResultSet;
import java.util.Vector;

import ra.ResidentAdvisor;

public class RAGetter 
{
	public Vector<ResidentAdvisor> ras = new Vector<ResidentAdvisor>();
	
	public RAGetter()
	{
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
				ra.setResId(rs.getInt("ResAdID"));
				ra.setStudentId(rs.getInt("StudentID"));
				ra.setBuildingID(rs.getInt("BuildingID"));
				ra.setFloorNum(rs.getInt("FloorNumber"));
				ra.setBuildingName(rs.getString("Name"));
				ras.add(ra);
			}
			
			for(int i=0; i<ras.size(); i++)
			{						
				query = "SELECT * FROM Students s INNER JOIN Housing h ON s.StudentID=h.StudentID"
						+ " WHERE s.StudentID = " + ras.get(i).getStudentId();
				rs = reader.executeQuery(query);
				if(rs.next())
				{
					ras.get(i).setName(rs.getString("FirstName") + " " + rs.getString("LastName"));
					ras.get(i).setGradYear(rs.getInt("GradYear"));
					ras.get(i).setMajor(rs.getString("Major"));		
					ras.get(i).setRoomNum(rs.getInt("RoomNumber"));
				}
			}		
			
			reader.close();
		}
		catch (Exception xe)
		{	
			System.out.println(xe);
			LogUtils.logException(xe);
		}
	}
	
	public Vector<ResidentAdvisor> getRAs(){
		return ras;
	}
}