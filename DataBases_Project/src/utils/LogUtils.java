package utils;

public class LogUtils {

	public static void logErrorMessage(String errorMessage){
		System.err.println(errorMessage);
	}
	
	public static void logException(Exception e){
		System.err.println(e.getMessage());
		e.printStackTrace();
	}
	
	public static void logDebug(String debugMessage){
		System.out.println(debugMessage);
	}
	
	public static void logFormatDebug(String formatString, String ... strings){
		logDebug(String.format(formatString, strings));
	}
}
