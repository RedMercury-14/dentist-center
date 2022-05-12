package by.mytest.main.validator.impl;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.mytest.main.model.Schedule;
import by.mytest.main.service.ScheduleService;
import by.mytest.main.service.ServiceException;
import by.mytest.main.validator.ScheduleValidation;
@Component
public class ScheduleValidationImpl implements ScheduleValidation{

	@Autowired
	ScheduleService scheduleService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Schedule.class.equals(clazz);
	}

	@Override
	public String validate(Object target) throws ValidationException {
		Schedule schedule = (Schedule) target;
		String error;			
		return error = null;
	}

}
