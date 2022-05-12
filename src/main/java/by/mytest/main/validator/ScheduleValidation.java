package by.mytest.main.validator;

import javax.xml.bind.ValidationException;

public interface ScheduleValidation {
	boolean supports(Class<?> clazz);
	String validate(Object target)throws ValidationException;

}
