package by.mytest.main.validator;

import java.time.LocalDate;

public interface DateValidation {
	boolean supports(Class<?> clazz);
	String validate(LocalDate dateStart, LocalDate dateFinish);

}
