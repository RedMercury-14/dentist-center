package by.mytest.main.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.mytest.main.constant.GlobalConstant;
import by.mytest.main.dao.DAOException;
import by.mytest.main.dao.ScheduleDAO;
import by.mytest.main.model.Schedule;
import by.mytest.main.model.User;
import by.mytest.main.service.ScheduleService;
import by.mytest.main.service.ServiceException;
import by.mytest.main.validator.ScheduleValidation;

@Service
public class ScheduleServiseImpl implements ScheduleService {

	@Autowired
	ScheduleDAO scheduleDAO;

	@Autowired
	ScheduleValidation scheduleValidation;

	@Override
	@Transactional
	public List<Schedule> getListSchedule() throws ServiceException {
		try {
			LocalDate dateStart = LocalDate.now().plusDays(1);
			List<Schedule> result = new ArrayList<Schedule>();
			for (int i = 0; i < GlobalConstant.DAYS_OF_SHOW; i++) {
				List<Schedule> schedules = scheduleDAO.getScheduleListFromDate(dateStart);
				schedules.stream().forEach(s -> result.add(s));
				dateStart = dateStart.plusDays(1);
			}
			return result;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	@Transactional
	public void saveOrUpdateSchedule(Schedule schedule) throws ServiceException {
		try {
			scheduleDAO.saveOrUpdateSchedule(schedule);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	@Transactional
	public Schedule getSchedule(int idSchedule) throws ServiceException {
		try {
			return scheduleDAO.getSchedule(idSchedule);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	@Transactional
	public void deleteSchedule(int idSchedule) throws ServiceException {
		try {
			scheduleDAO.deleteSchedule(idSchedule);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	@Transactional
	public void saveMonthSchedule(List<Schedule> schedules) throws ServiceException {

		schedules.stream().forEach(s -> {
			try {
				scheduleDAO.saveTargetSchedule(s);
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		});
	}

	@Override
	@Transactional
	public List<Schedule> getMonthSchedule(LocalDate dateStart, LocalDate dateFinish, int shiftStart, User user)
			throws ServiceException {

		List<Schedule> schedulesOfMonth = new ArrayList<Schedule>();
		LocalDate dateTarget = dateStart;

		while (dateTarget.isBefore(dateFinish) || dateTarget.equals(dateFinish)) {
			if (dateTarget.getDayOfWeek().equals(DayOfWeek.SATURDAY)
					|| dateTarget.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				dateTarget = dateTarget.plusDays(1);
				continue;
			}
			List<Schedule> schedulesOfDay = addDaySchedule(dateTarget, shiftStart, user);
			schedulesOfDay.stream().forEach(s -> schedulesOfMonth.add(s));
			dateTarget = dateTarget.plusDays(1);

			if (shiftStart == 1) {
				shiftStart = 2;
			} else {
				shiftStart = 1;
			}

		}

		return schedulesOfMonth;
	}

	private List<Schedule> addDaySchedule(LocalDate date, int shift, User user) {

		List<Schedule> schedulesOfDay = new ArrayList<Schedule>();
		switch (shift) {
		case 1:
			for (int i = GlobalConstant.HOUR_OF_START_FIRST_SHIFT; i <= GlobalConstant.HOUR_OF_FINISH_FIRST_SHIFT; i++) {
				LocalTime timeStart = LocalTime.of(i, 00, 00);
				schedulesOfDay.add(new Schedule(date, timeStart, null, null, user));
			}
			break;
		case 2:
			for (int i = GlobalConstant.HOUR_OF_START_SECOND_SHIFT; i <= GlobalConstant.HOUR_OF_FINISH_SECOND_SHIFT; i++) {
				LocalTime timeStart = LocalTime.of(i, 00, 00);
				schedulesOfDay.add(new Schedule(date, timeStart, null, null, user));
			}
			break;

		default:
			break;
		}
		return schedulesOfDay;

	}

	@Override
	@Transactional
	public List<Schedule> getFreeSchedule() throws ServiceException {
		try {
			LocalDate dateStart = LocalDate.now().plusDays(1);
			List<Schedule> result = new ArrayList<Schedule>();
			for (int i = 0; i < GlobalConstant.DAYS_OF_SHOW; i++) {
				List<Schedule> schedules = scheduleDAO.getScheduleListFromDate(dateStart);
				schedules.stream().filter(s -> s.getStatus() == null).forEach(s -> result.add(s));
				dateStart = dateStart.plusDays(1);
			}
			return result;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public List<Schedule> getScheduleListWhereLogin(User user) throws ServiceException {
		try {
			return scheduleDAO.getScheduleListFromLogin(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
