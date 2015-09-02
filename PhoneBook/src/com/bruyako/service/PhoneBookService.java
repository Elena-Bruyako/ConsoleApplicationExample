package com.bruyako.service;

import java.util.Collection;

import com.bruyako.model.Contact;

public interface PhoneBookService {

	public void addNewContact(Contact contact);
	
	public void editContact(int codeOperation, int indexContact, String value);
	
	public void editPhoneNumber(int codeOperation, Contact contact, int indexPhone, Object value);
	
	public void removeContact(Contact contact);
	
	public Contact getContactByIndex(int index);
	
	public Collection<Contact> getAllContacts();
	
	public Collection<Contact> searchContactByFirstName(String firstName);
	
	public Collection<Contact> searchContactByLastName(String lastName);
	
	public Collection<Contact> searchContactByPartName(String name);
	
	public Contact searchContactByNumberPhone(int numberPhone);
	
	public Collection<Contact> searchContactByAge(int age);
}
