package by.mytest.main.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import by.mytest.main.model.User;
import by.mytest.main.service.UserService;
import by.mytest.main.validator.UserValidator;
@Component
public class UserValidatorImpl implements UserValidator{
	@Autowired
	UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public String validate(Object target) {
		User user = (User) target;
		String error;	
	
		if (user.getLogin().length() < 3 || user.getLogin().length() > 32) {
			return error = "Invalid login length";
        }else if (userService.getUser(user.getLogin()) != null) {
        	return error = "A user with this username exists";
        }else if (user.getPassword().length() < 3 || user.getPassword().length() > 32) {
        	return error = "Invalid password length";
        }else if (!user.getConfirmPassword().equals(user.getPassword())) {
        	return error = "Passwords don't match";
        }else {
        	return error = null;
        }
		
	}

}
