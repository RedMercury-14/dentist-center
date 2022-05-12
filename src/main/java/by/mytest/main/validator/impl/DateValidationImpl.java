package by.mytest.main.validator.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import by.mytest.main.model.Schedule;
import by.mytest.main.validator.DateValidation;
@Component
public class DateValidationImpl implements DateValidation{

	@Override
	public boolean supports(Class<?> clazz) {
		return Schedule.class.equals(clazz);
	}

	@Override
	public String validate(LocalDate dateStart, LocalDate dateFinish) {
		String error;
		if(dateStart.isAfter(dateFinish)) {
			return error = "The start date cannot be later than the end date";
		}
		if (dateStart == null || dateFinish == null) {
			return error = "All fields are not filled";
		}
		return error = null;
	}

	

}
