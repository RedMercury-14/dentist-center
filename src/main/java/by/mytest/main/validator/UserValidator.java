package by.mytest.main.validator;

public interface UserValidator {
	boolean supports(Class<?> clazz);
	String validate(Object target);
	
}
