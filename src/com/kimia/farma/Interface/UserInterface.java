package com.kimia.farma.Interface;

import com.kimia.farma.model.User;

public interface UserInterface {
	User Login(User user);
	void DeleteUser(String key);
	void UpdateUser(User user);
	User getUser(String key);
	void insertUser(User u);
}
