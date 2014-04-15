package utils;

import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;

public class BuildingGetter {
	public Map<Integer, String> idBuildingNameMap = new TreeMap<Integer, String>();
	
	public BuildingGetter(){
		DBReader reader = new DBReader();
		ResultSet rs = null;
		try {
			String QueryString = "SELECT * FROM Buildings";
			System.out.println(QueryString);
			rs = reader.executeQuery(QueryString);
			while(rs.next()) {
				int x = rs.getInt("BuildingID");
				String y = rs.getString("name");
				System.out.println("id: " + x + "  name: " + y);
				idBuildingNameMap.put(x, y);
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
}