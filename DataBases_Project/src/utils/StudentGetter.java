package utils;

import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;

public class StudentGetter {
	public Map<Integer, String> idToName = new TreeMap<Integer, String>();
	
	public StudentGetter(){
		DBReader reader = new DBReader();
		ResultSet rs = null;
		try {
			String QueryString = "SELECT * FROM Students";
			rs = reader.executeQuery(QueryString);
			while(rs.next()) {
				idToName.put(rs.getInt("StudentID"), rs.getString("FirstName") + " " + rs.getString("LastName"));
		    }
			reader.close();
		}
		catch (Exception xe){	
			System.out.println(xe);
			LogUtils.logException(xe);
		}
	}
	
	public Map<Integer, String> getMap()
	{
		return idToName;
	}
}