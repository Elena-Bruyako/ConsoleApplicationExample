package com.bruyako.model;

public class Phone {

	private int numberPhone;
	private String typeNumberPhone;
	
	public Phone(int numberPhone, String typeNumberPhone) {
		this.numberPhone = numberPhone;
		this.typeNumberPhone = typeNumberPhone;
	}

	public int getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(int numberPhone) {
		this.numberPhone = numberPhone;
	}
	
	public String getTypeNumberPhone() {
		return typeNumberPhone;
	}

	public void setTypeNumberPhone(String typeNumberPhone) {
		this.typeNumberPhone = typeNumberPhone;
	}
}
