package com.bruyako;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.bruyako.model.Contact;
import com.bruyako.model.Phone;
import com.bruyako.model.User;
import com.bruyako.service.FilePhoneBooksService;
import com.bruyako.utils.Consts;
import com.bruyako.utils.DateUtils;
import com.bruyako.utils.UsersManager;

public class PhoneBook {
	
	private  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private  FilePhoneBooksService service = new FilePhoneBooksService();
	private  UsersManager usersManager = new UsersManager();

	public void startProgramDialog() throws Exception{
		service.restoreState();
		
		while (true) {
			System.out.println("Please, select your operation:\n");
			System.out.println(String.valueOf(Consts.REGISTERED) + " - Registered");
			System.out.println(String.valueOf(Consts.AUTHORIZ) + " - Authoriz");
			System.out.println(String.valueOf(Consts.OPTION_EXIT) + " - Exit \n");
			
			String option = br.readLine();
			int optionCode = Integer.parseInt(option);
			
			switch (optionCode) {
			case Consts.AUTHORIZ:
				if (isAuthoriz()) {
					startMainDialog();
				} else {
					System.out.println("User not found");
				}
				break;
			case Consts.REGISTERED:
				tryRegistered();
				startMainDialog();
				break;
				
			case Consts.OPTION_EXIT:
				System.out.println("Goodbye !!");
				System.exit(1);
				break;
			}
		}
	}
	
	private  void tryRegistered() throws Exception{
		System.out.println("Enter name");
		String name = br.readLine();
		
		System.out.println("Enter password");
		String password = br.readLine();
		
		User user = new User(name, password);
		usersManager.addNewUser(user);
	}
	
	private  boolean isAuthoriz() throws Exception{
		System.out.println("Enter name");
		String name = br.readLine();
		
		System.out.println("Enter password");
		String password = br.readLine();
		
		User user = new User(name, password);
		return usersManager.isUserFound(user);
	}
	
	private  void startMainDialog() throws Exception{
		
		while (true) {
			System.out.println("\nWelcome to The Best Phone Book Ever! Please, select your operation:\n");
			System.out.println(String.valueOf(Consts.OPTION_SHOWALL) + "  - Show all contacts");
			System.out.println(String.valueOf(Consts.OPTION_ADDNEW) + "  - Add new contact");
			System.out.println(String.valueOf(Consts.OPTION_EDIT) + "  - Edit contact");
			System.out.println(String.valueOf(Consts.OPTION_EDIT_NUM) + "  - Edit number phone");
			System.out.println(String.valueOf(Consts.OPTION_REMOVE_CONTACT) + "  - Remove contact");
			System.out.println(String.valueOf(Consts.OPTION_SEARCH_FNAME) + "  - Search contact by First Name");
			System.out.println(String.valueOf(Consts.OPTION_SEARH_LNAME) + "  - Search contact by Last Name");
			System.out.println(String.valueOf(Consts.OPTION_SEARCH_PARTNAME) + "  - Search contact by part of Name");
			System.out.println(String.valueOf(Consts.OPTION_SEARCH_NUMPHONE) + "  - Search contact by number phone");
			System.out.println(String.valueOf(Consts.OPTION_SEARCH_AGE) + " - Search contact by age");
			System.out.println(String.valueOf(Consts.OPTION_EXIT) + " - Exit \n");
			
			String option = br.readLine();
			int optionCode = Integer.parseInt(option);

			switch (optionCode) {
			case Consts.OPTION_SHOWALL:
				printContacts(service.getAllContacts());
				break;

			case Consts.OPTION_ADDNEW:		
				addNewContact();
				break;

			case Consts.OPTION_EDIT:
				editContact();
				break;

			case Consts.OPTION_EDIT_NUM:
				editNum();
				break;

			case Consts.OPTION_REMOVE_CONTACT:
				removeContact();
				break;
				
			case Consts.OPTION_SEARCH_FNAME:
				searchFirstName();
				break;

			case Consts.OPTION_SEARH_LNAME:
				searchLastName();
				break;

			case Consts.OPTION_SEARCH_PARTNAME:
				searchByPartName();
				break;

			case Consts.OPTION_SEARCH_NUMPHONE:
				searchByNumPhone();
				break;

			case Consts.OPTION_SEARCH_AGE:
				searchByAge();
				break;
				
			case Consts.OPTION_EXIT:
				service.saveState();
				System.out.println("Goodbye !!");
				System.exit(1);
				break;
			}
		}
	}
	
	private  void addNewContact() throws Exception {
		
		System.out.println("Enter First Name");
		String fName = br.readLine();
		
		System.out.println("Enter Last Name");
		String lName = br.readLine();
		
		System.out.println("Enter email");
		String mail = br.readLine();
		
		System.out.println("Enter birth date in yyyy-MM-dd format");
		String dateString = br.readLine();
		
		Date date = DateUtils.fromStringToDate(dateString);
		System.out.println("Enter address");
		
		String adres = br.readLine();

		Contact newContact = new Contact(fName, lName, mail, date, adres);
		
		do {
			
			System.out.println("Enter phone type");
			String phoneType = br.readLine();
			
			System.out.println("Enter phone");
			String numPhone = br.readLine();
			
			
			Phone phone = new Phone(Integer.parseInt(numPhone), phoneType);
			newContact.addPhoneNumber(phone);	
			
			System.out.println(phoneType + " : " + numPhone + " was added :)");	
			System.out.println("add another phone? y/n");
			String code = br.readLine();
			
	        if(code.equals("n") || code.equals("N") || code.equals("No") || code.equals("NO")){
	        	 break;
	        } 
	        
		} while (true);
		
		service.addNewContact(newContact);	
		
		System.out.println("new contact was added :)");
		printContact(newContact);
	}
	
	private  void editContact() throws Exception {
		System.out.println("\nPlease, enter the index of the contact, that you want to change\n");
		String num = br.readLine();
		System.out.println(Consts.EDIT_FIRSTNAME + " Change the first name");
		System.out.println(Consts.EDIT_LASTNAME + " Change the last name");
		System.out.println(Consts.EDIT_EMAIL + " Change the email");
		System.out.println(Consts.EDIT_BIRTHDATE + " Change the birth date");
		System.out.println(Consts.EDIT_ADDRESS + " Change the address");
		String codeContact = br.readLine();
		System.out.println("Please, enter the value that you want to change.");
		String value = br.readLine();
		service.editContact(Integer.parseInt(codeContact), Integer.parseInt(num), value);
		System.out.println("contact was added :)");
	}
	
	private  void editNum() throws Exception {
		System.out.println("Please, enter the index of the contact, that you want to change");
		String index = br.readLine();
		
		System.out.println("Please, enter the index number phone, that you want to change");
		String indexPhone = br.readLine();
		
		System.out.println(Consts.EDIT_NUMPHONE + " Change the number phone");
		System.out.println(Consts.EDIT_TYPEPHONE + " Change the type number phone");
		String codePhone = br.readLine();
		
		System.out.println("Please, enter the value that you want to change.");
		String valuePhone = br.readLine();
		
		Contact contact = service.getContactByIndex(Integer.parseInt(index));
		service.editPhoneNumber(Integer.parseInt(codePhone), contact, Integer.parseInt(indexPhone), valuePhone);
		System.out.println("phone was added :)");
	}
	
	private  void removeContact() throws Exception {
		System.out.println("Please, enter the index of the contact, that you want to remove");
		String indexContactStr = br.readLine();
		int indexContact = Integer.parseInt(indexContactStr);
		Contact contactRemove = service.getContactByIndex(indexContact);
		
		System.out.println("Contact removed :(.");
		printContact(contactRemove);
		service.removeContact(contactRemove);
	}
	
	private  void searchFirstName() throws Exception {
		System.out.println("To find a contact, type the First name");
		String firstName = br.readLine();
		Collection<Contact> results = service.searchContactByFirstName(firstName);
		printContacts(results);
	}
	
	private  void searchLastName() throws Exception {
		System.out.println("To find a contact, type the Last name");
		String lastName = br.readLine();
		Collection<Contact> results = service.searchContactByLastName(lastName);
		printContacts(results);
	}
	
	private  void searchByPartName() throws Exception {
		System.out.println("To find a contact, type the part name");
		String partName = br.readLine();
		Collection<Contact> results = service.searchContactByPartName(partName);
		printContacts(results);
	}
	
	private  void searchByNumPhone() throws Exception {
		System.out.println("To find a contact, type the number phone");
		String number = br.readLine();
		Contact result = service.searchContactByNumberPhone(Integer.parseInt(number));
		printContact(result);
	}
	
	private  void searchByAge() throws Exception {
		System.out.println("To find a contact, type the contact age");
		String age = br.readLine();
		Collection<Contact> results = service.searchContactByAge(Integer.parseInt(age));
		printContacts(results);
	}
	
	private  void printContacts(Collection<Contact> contacts) {
		
		if(contacts != null && contacts.size() > 0){
			
			for(Contact contact : contacts){
				printContact(contact);
			}
			
		} else {
			System.out.println("Uuups. No results for displaying :(.");
		}
	}
	
	private  void printContact(Contact contact) {
		
		if(contact != null){
			System.out.println("=======================");
			System.out.println("index: " + getIndexByContact(contact));
			System.out.println(contact.getFirstName() + " " + contact.getLastName());
			System.out.println(contact.getEmail());
			System.out.println(contact.getBirthDate());
			System.out.println(contact.getAddress());
			printPhone(contact.getAllPhones());
			System.out.println("=======================");
		} else {
			System.out.println("Uuups. No result for displaying :(.");
		}
	}
	
	private  void printPhone(List<Phone> phoneList) {
		for (Phone phone : phoneList){
			System.out.println(phone.getTypeNumberPhone() + ": " + phone.getNumberPhone());
		}
	}
	
	private  int getIndexByContact(Contact contact){
		for(int i = 0; i < service.getAllContacts().size(); i++){
			Contact currentContact = service.getContactByIndex(i);
			if(currentContact.equals(contact))
				return i;
		}
		
		return -1;
	}
}
