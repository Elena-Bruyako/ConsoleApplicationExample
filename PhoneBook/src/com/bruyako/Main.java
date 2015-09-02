package com.bruyako;

import java.text.ParseException;

public class Main {

	public static void main(String[] args){
		
		PhoneBook phoneBook = new PhoneBook();
		
		try {
			phoneBook.startProgramDialog();
		} catch (ParseException e){
			System.out.println("Uuups. Incorrect data type. Your application was closed. Please try again :(.");
		} catch (Exception e) {
			System.out.println("Uuups. Something was wrong. Your application was closed. Please try again :(.");
		}
	}
}
