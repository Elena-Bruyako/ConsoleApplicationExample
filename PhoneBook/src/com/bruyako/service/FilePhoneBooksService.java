package com.bruyako.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.bruyako.model.Contact;
import com.bruyako.utils.Consts;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FilePhoneBooksService extends SaveRestorePhoneBookService{
	
	@Override
	public void saveState() {
		
		Gson gson = new Gson();
		String formatGson = gson.toJson(contacts);
		
		try {
			PrintWriter writer = new PrintWriter(Consts.FILE_NAME);
			writer.println(formatGson);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void restoreState() {
		try {
			readFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void readFromFile() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(Consts.FILE_NAME));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        String result = sb.toString();
	        Gson gson = new Gson();
	        List<Contact> fileContacts = gson.fromJson(result, new TypeToken<ArrayList<Contact>>(){}.getType());
	        
	        if(fileContacts != null){
	        	contacts = fileContacts;
	        }
	        
	    } finally {
	        br.close();
	    }
	}
}
