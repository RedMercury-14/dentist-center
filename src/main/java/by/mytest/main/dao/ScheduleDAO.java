package by.mytest.main.dao;

import java.time.LocalDate;
import java.util.List;

import by.mytest.main.model.Schedule;
import by.mytest.main.model.User;

public interface ScheduleDAO {
	
	List<Schedule> getScheduleList() throws DAOException; 

	void saveOrUpdateSchedule(Schedule schedule) throws DAOException; 

	Schedule getSchedule(int idSchedule) throws DAOException; 
	
	void saveTargetSchedule(Schedule schedule) throws DAOException; 

	void deleteSchedule(int idSchedule) throws DAOException; 
	
	List<Schedule> getScheduleListFromDate(LocalDate date) throws DAOException;
	
	List<Schedule> getScheduleListFromLogin(User user) throws DAOException;

}
