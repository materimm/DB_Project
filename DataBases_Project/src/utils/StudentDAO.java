package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import student.Student;

public class StudentDAO 
{
		
	public Vector<Student> getStudents()
	{
		Vector<Student> studentList = new Vector<Student>();
		DBReader reader = new DBReader();
		ResultSet rs = null;
		
		try 
		{
			String query = "SELECT * FROM Students";
			rs = reader.executeQuery(query);
			while(rs.next())
			{
				Student st = new Student();
				st.setStudentID(rs.getInt("StudentID"));
				st.setFirstName(rs.getString("FirstName"));
				st.setLastName(rs.getString("LastName"));
				st.setGradYear(rs.getInt("GradYear"));
				st.setMajor(rs.getString("Major"));
				
				studentList.add(st);
		    }
			
			for(int i=0; i<studentList.size(); i++)
			{			
				query = "SELECT * FROM HOUSING WHERE StudentID = " + studentList.get(i).getStudentID();
				rs = reader.executeQuery(query);
				if(rs.next())
				{
					studentList.get(i).setBuildingID(rs.getInt("BuildingID"));
					studentList.get(i).setFloorNum(rs.getInt("FloorNumber"));
					studentList.get(i).setRoomNum(rs.getInt("RoomNumber"));
				}
			}
			reader.close();
			return studentList;
		}
		catch (Exception xe)
		{	
			System.out.println(xe);
			LogUtils.logException(xe);
			return null;
		}
	}
	
	public Student setStudent(Student st)
	{
		DBWriter writer = new DBWriter();
		DBReader reader = new DBReader();
		ResultSet rs = null;
		
		try 
		{
			String query = "INSERT INTO Students (FirstName, LastName, GradYear, Major) VALUES ('"
					+ st.getFirstName() + "', '" + st.getLastName() + "', " + st.getGradYear() + ", '" + st.getMajor() + "')";
			writer.insertQuery(query);
			
			query = "SELECT max(StudentID) FROM Students";
			rs = reader.executeQuery(query);
			if(rs.next())
			{
				st.setStudentID(rs.getInt("max(StudentID)"));
			}
			writer.close();
			reader.close();
			
			return st;
			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public void setHousing(Student st)
	{
		DBWriter writer = new DBWriter();
		DBReader reader = new DBReader();
		ResultSet rs = null;
		int raID=99999;
		
		try 
		{
			String query = "SELECT * FROM ResidentAdvisors WHERE BuildingID = " + st.getBuildingID() + " AND "
					+ "FloorNumber = " + st.getFloorNum();
			rs = reader.executeQuery(query);

			if(rs.next())
			{
				raID = rs.getInt("ResAdID");
			}
			else
			{
				raID = 1;
			}
			
			query = "INSERT INTO Housing (StudentID, BuildingID, FloorNumber, RoomNumber, RoomMateID, ResAdID) VALUES ("
					+ st.getStudentID() + ", " + st.getBuildingID() + ", " + st.getFloorNum() + ", " 
					+ st.getRoomNum() + ", 9999999, " + raID + ")" ;
			writer.insertQuery(query);
			
			reader.close();
			writer.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void removeStudent(Student s)
	{
		DBWriter writer = new DBWriter();
		String query = "DELETE FROM Housing WHERE StudentID = " + s.getStudentID();
		writer.insertQuery(query);
		
		query = "DELETE FROM ResidentAdvisors WHERE StudentID = " + s.getStudentID();
		writer.insertQuery(query);
		
		query = "DELETE FROM ResidentDirectors WHERE StudentID = " + s.getStudentID();
		writer.insertQuery(query);
		
		query = "DELETE FROM Students WHERE StudentID = " + s.getStudentID();
		writer.insertQuery(query);
			
		try {
			writer.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}