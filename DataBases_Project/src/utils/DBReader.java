package utils;

import java.sql.*;

/**
 * Class for abstracting some of the messier details of the JDBC API.  All of the database connectivity is handled
 * internally; furthermore, running queries is nearly identical to the standard JDBC way
 * 
 * Implements the AutoCloseable interface which allows for automatic, quick garbage collection after
 * an instance of the class is created.  This class can be used as follows:
 * <code>
 * try (DBReader reader = new DBReader()){
 * 	doSomething(reader);
 * } catch (SQLException e){
 * 	handleException(e);
 * }
 * </code>
 * This statement will handle garbage collection and close the Reader intelligently.
 * 
 * @author Nickalaus Painter
 *
 */
public class DBReader implements AutoCloseable{
	
	/* These are part of the SQL API's in Java for database connectivity */
	protected Statement stmt;
	protected Connection conn;
	
	/* This URL corresponds to the URL of the MySQL Database */
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/project";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "minions";
	
	public DBReader(){
		initialize();
	}
	
	/**
	 * Executes the given SQL query on the database that the DBReader is attached to.
	 * Returns null if the query produced an error.
	 * @param query
	 * @return
	 */
	
	public ResultSet executeQuery(String query){
		try{
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			LogUtils.logException(e);
			return null;
		}
	}
	
	/**
	 * Initializes all of the JDBC API members that are needed to communicate with the database.
	 * @return
	 */
	public boolean initialize(){
		try {
			/* Establish a new instance of the JDBC Driver for this particular DBReader */
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			/* Use the driver to establish a connection using the credentialsk, make it read only */
			conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			conn.setReadOnly(true);
			
			/* Attach the statement to the connection */
			stmt = conn.createStatement();
			
			/* If we get here then everything went off without a hitch */
			return true;
			
			/* This is a multi-catch, it performs the same action for every exception in the list */
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			LogUtils.logException(e);
			return false;
		}
	}
	
	/**
	 * This function MUST be called when the user is done using the database reader, otherwise
	 * there will be a memory leak.
	 * 
	 * @throws SQLException If the connection or statement does not close properly
	 */
	public void close() throws SQLException {
		stmt.close();
		conn.close();
	}
	
	
	public static boolean TEST_hasNextResult(ResultSet rs){
		try {
			if(rs.isBeforeFirst()){
				return true;
			}
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			LogUtils.logException(e);
			return false;
		}
		return false;
	}
}
