package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import building.Building;

public class BuildingDAO 
{

	
	public Vector<Building> getBuildings()
	{
		DBReader reader = new DBReader();
		ResultSet rs = null;
		
		Vector<Building> buildingList = new Vector<Building>();
		
		try 
		{
			String query = "SELECT * FROM Buildings";
			rs = reader.executeQuery(query);
			while(rs.next()) 
			{
				Building b = new Building();
				b.setBuildingID(rs.getInt("BuildingID"));
				b.setName(rs.getString("Name"));
				b.setNumApartments(rs.getInt("NumberApartments"));
				b.setNumFloors(rs.getInt("NumberFloors"));
				b.setNumRooms(rs.getInt("NumberRooms"));
				
				buildingList.add(b);
		    }
			reader.close();
			
			return buildingList;
		}
		catch (Exception xe){	
			System.out.println(xe);
			LogUtils.logException(xe);
			return null;
		}
	}
	
	public void setBuilding(Building b)
	{
		DBWriter writer = new DBWriter();
		
		String query = "INSERT INTO Buildings (Name, NumberFloors, NumberRooms, NumberApartments) VALUES ('"
				+ b.getName() + "', " + b.getNumFloors() + ", " + b.getNumRooms() + ", " + b.getNumApartments() + ")";
		writer.insertQuery(query);
		
		try {
			writer.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}