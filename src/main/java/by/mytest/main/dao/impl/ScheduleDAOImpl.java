package by.mytest.main.dao.impl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.mytest.main.dao.DAOException;
import by.mytest.main.dao.ScheduleDAO;
import by.mytest.main.model.Schedule;
import by.mytest.main.model.User;
@Repository
public class ScheduleDAOImpl implements ScheduleDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	private static final String queryGetList = "from Schedule order by id";
	@Override
	public List<Schedule> getScheduleList() throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Schedule> theSchedule = currentSession.createQuery(queryGetList, Schedule.class);
		List <Schedule> Schedules = theSchedule.getResultList();
		return Schedules;
	}

	@Override
	public void saveOrUpdateSchedule(Schedule schedule) throws DAOException  {
		Session currentSession = sessionFactory.getCurrentSession();
		try {			
			currentSession.saveOrUpdate(schedule);
		} catch (Exception e) {			
			throw new DAOException(e);
		}		
	}

	@Override
	public Schedule getSchedule(int idSchedule) throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		Schedule schedule = currentSession.get(Schedule.class, idSchedule);
		return schedule;
	}
	
	private static final String queryDeleteSchedule = "delete from Schedule where idSchedule=:setId";
	@Override
	public void deleteSchedule(int idSchedule) throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = 
				currentSession.createQuery(queryDeleteSchedule);
		theQuery.setParameter("setId", idSchedule);
		theQuery.executeUpdate();
	}
	
	
	private static final String querySaveTargetSchedule = "FROM Schedule S WHERE S.localDate =:target";
	@Override
	public void saveTargetSchedule(Schedule schedule) throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery(querySaveTargetSchedule);				
		theQuery.setParameter("target", schedule.getLocalDate());
		List <Schedule> schedules =  theQuery.list();
		if(!schedules.contains(schedule)) {
			try {			
				currentSession.save(schedule);
			} catch (Exception e) {			
				throw new DAOException(e);
			}
		}
	}
	private static final String queryGetScheduleListFromDate = "FROM Schedule S WHERE S.localDate =:target";
	@Override
	public List<Schedule> getScheduleListFromDate(LocalDate date) throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();	
		Query theQuery = currentSession.createQuery(queryGetScheduleListFromDate);
		theQuery.setParameter("target", date);
		List <Schedule> schedules = theQuery.list();
		return schedules;
	}
	
	private static final String queryGetScheduleListFromLogin = "FROM Schedule S WHERE S.status =:target";
	@Override
	public List<Schedule> getScheduleListFromLogin(User user) throws DAOException {
		Session currentSession = sessionFactory.getCurrentSession();	
		Query theQuery = currentSession.createQuery(queryGetScheduleListFromLogin);
		theQuery.setParameter("target", user.getLogin());
		List <Schedule> schedules = theQuery.list();
		return schedules;
	}
	
}
