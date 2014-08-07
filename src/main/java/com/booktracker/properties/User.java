package com.booktracker.properties;

import java.util.Map;

public class User {

	private static Map<String, String> userEmail;

	private static String user;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		User.user = user;
	}
	
	public static Map<String, String> getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(Map<String, String> userEmail) {
		User.userEmail = userEmail;
	}

}
