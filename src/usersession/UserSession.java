package usersession;

public class UserSession {
	/** ¸â¹ö º¯¼ö */
	private static String id = null;
	private static String name= null;
	private static int gender;
	private static int dailyHeart=0;
	private static int totalHeart = 0;
	private static String birthDate=null;
	private static String secondId= null;
	
	
	
	/** Getter Setter */

	
	public static String getId() {
		return id;
	}
	public static void setId(String id) {
		UserSession.id = id;
	}
	public static String getName() {
		return name;
	}
	public static void setName(String name) {
		UserSession.name = name;
	}
	public static int getGender() {
		return gender;
	}
	public static void setGender(int gender) {
		UserSession.gender = gender;
	}
	public static int getDailyHeart() {
		return dailyHeart;
	}
	public static void setDailyHeart(int dailyHeart) {
		UserSession.dailyHeart = dailyHeart;
	}
	public static int getTotalHeart() {
		return totalHeart;
	}
	public static void setTotalHeart(int totalHeart) {
		UserSession.totalHeart = totalHeart;
	}
	public static String getBirthDate() {
		return birthDate;
	}
	public static void setBirthDate(String birthDate) {
		UserSession.birthDate = birthDate;
	}
	
	public static String getSecondId() {
		return secondId;
	}
	public static void setSecondId(String secondId) {
		UserSession.secondId = secondId;
	}
	
	



	

}