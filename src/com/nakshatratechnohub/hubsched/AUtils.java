package com.nakshatratechnohub.hubsched;

import java.util.Random;

public class AUtils {
	static Random random = new Random();

	static String[] roomNames = { "Presentation Hall", "Conference Room 1", "Boardroom A", "Boardroom B" };

	static String[] givenFirstNames = { "Raj", "Amit", "Sonia", "Rahul", "Priya", "Sanjay", "Neha", "Rajesh", "Anita",
			"Arun", "Anjali", "Manoj", "Kavita", "Vikram", "Pooja" };

	static String[] givenLastNames = { "Sharma", "Verma", "Singh", "Gupta", "Kumar", "Chopra", "Patel", "Jain", "Mehta",
			"Pandey" };
	static String userName;

	public static String generateRandomRoomName() {

		int randomRoomNameIndex = random.nextInt(roomNames.length);

		String roomName = roomNames[randomRoomNameIndex];

		return roomName;
	}

	public static String generateRandomRoomNo() {
		Random random = new Random();
		int roomNumber = 10 + random.nextInt(900);
		return String.valueOf(roomNumber);

	}

	public static String generateRandomFloorNo() {
		Random random = new Random();
		int floorNumber = 1 + random.nextInt(10);
		return String.valueOf(floorNumber);
	}

	public static String generateRandomSeatCapacity() {
		int seatCapacity = 10 + random.nextInt(91);
		return String.valueOf(seatCapacity);
	}

	public static String generateRandomId() {
		int randomID = 1000 + random.nextInt(9000);
		return String.valueOf(randomID);

	}

	public static String generateRandomName() {

//		String[] givenFirstNames = { "Raj", "Amit", "Sonia", "Rahul", "Priya", "Sanjay", "Neha", "Rajesh", "Anita",
//				"Arun", "Anjali", "Manoj", "Kavita", "Vikram", "Pooja" };
//		String[] givenLastNames = { "Sharma", "Verma", "Singh", "Gupta", "Kumar", "Chopra", "Patel", "Jain", "Mehta",
//				"Pandey" };

		int randomFirstNameIndex = random.nextInt(givenFirstNames.length);
		int randomLastNameIndex = random.nextInt(givenLastNames.length);

		 userName = givenFirstNames[randomFirstNameIndex];
		String randomLastName = givenLastNames[randomLastNameIndex];

		// Concatenating first name and last name for the name field
		String name = userName + " " + randomLastName;
		return name;
	}

	public static String generateRandomEmail() {

		// Generating a random two-digit integer
		int randomLastInt = 10 + random.nextInt(90); // Generates a number between 10 and 99 (inclusive)
		String randomInt = String.valueOf(randomLastInt);

		String email = userName.toLowerCase() + randomInt + "@example.com";
		return email;
	}

	public static String generateRandomMobileNumber() {
		Random random = new Random();
		StringBuilder mobileNumber = new StringBuilder();

		// The first digit of a phone number should not be zero
		mobileNumber.append(6 + random.nextInt(4)); // Adding a random digit between 1 and 9 as the first digit

		// Generating the remaining 9 digits
		for (int i = 0; i < 9; i++) {
			mobileNumber.append(random.nextInt(10)); // Adding random digits between 0 and 9
		}

		return mobileNumber.toString();
	}

	public static void sleepNow(int mSecond) {
		try {
			Thread.sleep(mSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
