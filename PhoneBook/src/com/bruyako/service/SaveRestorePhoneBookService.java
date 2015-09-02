package com.bruyako.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.bruyako.model.Contact;
import com.bruyako.model.Phone;
import com.bruyako.utils.Consts;
import com.bruyako.utils.DateUtils;

public abstract class SaveRestorePhoneBookService implements PhoneBookService{

	protected List<Contact> contacts = new ArrayList<Contact>();
	
	public abstract void saveState();
	public abstract void restoreState();
	
	@Override
	public void addNewContact(Contact contact) {
		
		contacts.add(contact);
	}
	
	@Override
	public void editContact(int codeOperation, int indexContact, String value) {
		
		switch (codeOperation) {
			case Consts.EDIT_FIRSTNAME:
				contacts.get(indexContact).setFirstName(value);
				break;
			case Consts.EDIT_LASTNAME:
				contacts.get(indexContact).setLastName(value);
				break;
			case Consts.EDIT_EMAIL:
				contacts.get(indexContact).setEmail(value);
				break;
			case Consts.EDIT_BIRTHDATE:
				try{
					Date date = DateUtils.fromStringToDate(value);
					contacts.get(indexContact).setBirthDate(date);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case Consts.EDIT_ADDRESS:
				contacts.get(indexContact).setAddress(value);
				break;
		}
	}
	
	@Override
	public void editPhoneNumber(int codeOperation, Contact contact, int indexPhone, Object value) {
		
		switch (codeOperation) {
		case Consts.EDIT_NUMPHONE:
			String phone = (String) value;
			contact.getPhoneByIndex(indexPhone).setNumberPhone(Integer.parseInt(phone));
			break;
		case Consts.EDIT_TYPEPHONE:
			contact.getPhoneByIndex(indexPhone).setTypeNumberPhone((String) value);
			break;
		}
	}
	
	@Override
	public void removeContact(Contact contact) {
		
		contacts.remove(contact);
	}
	
	@Override
	public Contact getContactByIndex(int index) {
		return contacts.get(index);
	}
	
	@Override
	public Collection<Contact> getAllContacts() {
		
		return contacts;
	}
	
	@Override
	public Collection<Contact> searchContactByFirstName(String firstName) {
		
		List<Contact> contactByFirstName = new ArrayList<Contact>();
		for (Contact contact : contacts) {
			if (contact.getFirstName().toLowerCase().equals(firstName.toLowerCase())) {
				contactByFirstName.add(contact);
			}
		}
		return contactByFirstName;
	}
	
	@Override
	public Collection<Contact> searchContactByLastName(String lastName) {
		
		List<Contact> contactByLastName = new ArrayList<Contact>();
		for (Contact contact : contacts) {
			if (contact.getLastName().toLowerCase().equals(lastName.toLowerCase())) {
				contactByLastName.add(contact);
			}
		}
		return contactByLastName;
	}
	
	@Override
	public Collection<Contact> searchContactByPartName(String name) {
		
		List<Contact> contactByPartName = new ArrayList<Contact>();
		for (Contact contact : contacts) {
			String fullName = contact.getFirstName() + " " + contact.getLastName();
			if (fullName.toLowerCase().contains(name.toLowerCase())) {
				contactByPartName.add(contact);
			}
		}
		return contactByPartName;
	}
	
	@Override
	public Contact searchContactByNumberPhone(int numberPhone) {
		
		for (Contact contact : contacts) {
			for (Phone numPhone : contact.getAllPhones()) {
				if (numPhone.getNumberPhone() == numberPhone) {
					return contact;
				}
			}
		}
		return null;
	}
	
	@Override
	public Collection<Contact> searchContactByAge(int age) {
		
		List<Contact> contactByAge = new ArrayList<Contact>();
		Date date = new Date();
		for (Contact contact : contacts) {
			int differentYear = date.getYear() - contact.getBirthDate().getYear();
			if (differentYear == age) {
				contactByAge.add(contact);
			}
		}
		return contactByAge;
	}
}
