package by.mytest.main.service;

import java.util.List;

import by.mytest.main.model.User;

public interface UserService {
	
	public List<User> getUsers();

	public void saveUser(User user);

	public User getUser(String login);

	public void deleteUser(String login);
	public boolean isUser(User user);

}
