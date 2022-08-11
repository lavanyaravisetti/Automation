package com.google.automation.utils;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
// A common utils class to generate random alphanumerics and project specific strings

public class CommonUtils {
	public static String getRandomAlphaNumeric() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	public static String getRandomAlphabetic() {
		return RandomStringUtils.randomAlphabetic(10);
	}

	public static String getRandomNumeric() {
		return RandomStringUtils.randomNumeric(10);
	}

	public static String getRandomEmailId() {
		return "John" + RandomStringUtils.randomNumeric(10) + ".Smith@test.com";
	}

	public static String getRandomFirstName() {
		return "John" + RandomStringUtils.randomNumeric(10);
	}

	public static String getRandomlastName() {
		return "Smith" + RandomStringUtils.randomNumeric(10);
	}

	public static int getRandomHotelId() {
		return 1039900000 + Integer.parseInt(RandomStringUtils.randomNumeric(5));
	}

	public static String timeConversion(long millis) {
		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
				TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
				TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
		String strMilli = TimeUnit.MILLISECONDS.toMillis(millis) % TimeUnit.MINUTES.toMillis(1) + "";
		/*
		 * if(strMilli.length()>=3){ strMilli=strMilli.substring(0, 3); } else
		 * strMilli="00";
		 */
		hms = hms + ":" + strMilli;
		return hms;

	}

}