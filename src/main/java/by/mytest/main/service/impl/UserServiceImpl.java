package by.mytest.main.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.mytest.main.dao.RoleDAO;
import by.mytest.main.dao.UserDAO;
import by.mytest.main.model.Role;
import by.mytest.main.model.User;
import by.mytest.main.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional
	public List<User> getUsers() {
		return userDAO.getUserList();
	}
	@Transactional
	public void saveUser(User user) {
		Role role = roleDAO.getRole(1);
		Set<Role>roles = new HashSet<Role>();
		roles.add(role);
		user.setRoles(roles);
		user.setEnabled(true);	
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userDAO.saveUser(user);	
	}
	@Transactional
	public User getUser(String login) {
		return userDAO.getUser(login);
	}
	@Transactional
	public void deleteUser(String login) {
		userDAO.deleteUser(login);
		
	}
	@Transactional
	public boolean isUser(User user) {
		List<User> trueUsers = userDAO.getUserList();
		for (User userTrue : trueUsers) {
			if(userTrue.getLogin().equals(user.getLogin()) && userTrue.getPassword().equals(user.getPassword())) {
				return true;				
			}
		}
		return false;
		
	} 

}
