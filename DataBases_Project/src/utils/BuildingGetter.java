package utils;

import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class BuildingGetter {
	public Map<Integer, String> idBuildingNameMap = new TreeMap<Integer, String>();
	public Vector<Integer> floorNums = new Vector<Integer>();
	
	public BuildingGetter(){
		DBReader reader = new DBReader();
		ResultSet rs = null;
		try {
			String QueryString = "SELECT * FROM Buildings";
			rs = reader.executeQuery(QueryString);
			while(rs.next()) {
				idBuildingNameMap.put(rs.getInt("BuildingID"), rs.getString("name"));
				floorNums.add(rs.getInt("NumberFloors"));
		    }
			reader.close();
		}
		catch (Exception xe){	
			System.out.println(xe);
			LogUtils.logException(xe);
		}
	}
	
	public Map<Integer, String> getMap(){
		return idBuildingNameMap;
	}
	
	public Vector<Integer> getVec(){
		return floorNums;
	}
}