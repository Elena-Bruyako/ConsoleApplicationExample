package com.bruyako.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Contact {

	private String firstName;
	private String lastName;
	private String email;
	private Date birthDate;
	private String address;
	private List<Phone> phoneList = new ArrayList<Phone>();

	public Contact(String firstName, String lastName, String email,	Date birthDate, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
		this.address = address;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public void addPhoneNumber(Phone numberPhone) {
		
		phoneList.add(numberPhone);
	}
	
	public void removePhoneNumber(Phone numberPhone) {
		
		phoneList.remove(numberPhone);
	}
	
	public List<Phone> getPhonesByType(String typeNumberPhone){
		
		List<Phone> typePhoneList = new ArrayList<Phone>();
		for (Phone phone : phoneList) {
			if (phone.getTypeNumberPhone().equals(typeNumberPhone)) {
				typePhoneList.add(phone);
			}
		}
		return typePhoneList;
	}
	
	public List<Phone> getAllPhones(){
		
		return phoneList;
	}
	
	public Phone getPhoneByIndex(int index) {
		
		return phoneList.get(index);
	}
}
