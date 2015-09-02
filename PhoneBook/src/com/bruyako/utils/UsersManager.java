package com.bruyako.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.bruyako.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UsersManager {

	private List<User> userList;
	
	public UsersManager() {
		try{
			readUser();
		}
		catch (IOException e) {
			e.printStackTrace();
			userList = new ArrayList<User>();
		}
 	}
	
	public boolean isUserFound(User user) {
		for (User currentUser : userList) {
			if (currentUser.getPassword().equals(user.getPassword()) && currentUser.getUserName().equals(user.getUserName())) {
				return true;
			}
		}
		return false;
	}
	
	public void addNewUser(User user) {
		userList.add(user);
		saveUser();
	}
	
	private void saveUser() {
		Gson gson = new Gson();
		String formatGson = gson.toJson(userList);
		
		try {
			PrintWriter writer = new PrintWriter(Consts.FILE_NAME_USERS);
			writer.println(formatGson);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void readUser() throws IOException{
		
	BufferedReader br = new BufferedReader(new FileReader(Consts.FILE_NAME_USERS));
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
	        List<User> fileUsers = gson.fromJson(result, new TypeToken<ArrayList<User>>(){}.getType());
	        
	        if(fileUsers!= null){
	        	userList = fileUsers;
	        } else {
	        	userList = new ArrayList<User>();
	        }
	        
	    } finally {
	        br.close();
	    }
	}
}
