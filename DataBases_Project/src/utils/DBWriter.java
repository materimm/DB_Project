package utils;

import java.sql.SQLException;

public class DBWriter extends DBReader {

	public DBWriter(){
		super();
		try {
			conn.setReadOnly(false);
		} catch (SQLException e) {
			LogUtils.logException(e);
		}
	}
	
	public boolean insertQuery(String query){
		try {
			int result = stmt.executeUpdate(query);
			if(result == 0){
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			LogUtils.logException(e);
			return false;
		}
	}
	
	public boolean updateQuery(String query){
		return insertQuery(query);
	}
}
