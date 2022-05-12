package by.mytest.main.service;

import java.time.LocalDate;
import java.util.List;

import by.mytest.main.dao.DAOException;
import by.mytest.main.model.Schedule;
import by.mytest.main.model.User;

public interface ScheduleService {
	
	List<Schedule> getListSchedule() throws ServiceException;
	void saveOrUpdateSchedule(Schedule schedule) throws ServiceException;
	Schedule getSchedule(int idSchedule) throws ServiceException;
	void deleteSchedule(int idSchedule) throws ServiceException;
	List<Schedule> getMonthSchedule(LocalDate dateStart, LocalDate dateFinish, int shiftStart, User user) throws ServiceException;
	void saveMonthSchedule(List<Schedule> schedules) throws ServiceException;
	List<Schedule> getFreeSchedule() throws ServiceException;
	List<Schedule> getScheduleListWhereLogin(User user) throws ServiceException;

}
