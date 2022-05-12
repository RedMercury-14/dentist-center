package by.mytest.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.mytest.main.dao.UserDAO;
import by.mytest.main.model.User;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserDAO userDAO;	

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.getUser(username);
		UserBuilder userBuilder = null;
		if(user != null) {
			userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
			userBuilder.disabled(!user.isEnabled());
			userBuilder.password(user.getPassword());
			String[] authorities = user.getRoles().stream().map(a-> a.getAuthority()).toArray(String[]::new);
			userBuilder.authorities(authorities);			
		}else {
			System.out.println("User not found.");
			throw new UsernameNotFoundException("User not found.");
		}
		return userBuilder.build();
	}
}
