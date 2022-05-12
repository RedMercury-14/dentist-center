package by.mytest.main.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.mytest.main.dao.UserDAO;
import by.mytest.main.model.User;
@Repository
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final String queryGetList = "from User order by Login";
	public List<User> getUserList() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> theRole = currentSession.createQuery(queryGetList, User.class);
		List <User> users = theRole.getResultList();
		return users;
	}

	public void saveUser(User user) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(user);
		
	}

	public User getUser(String loginUse) {
		Session currentSession = sessionFactory.getCurrentSession();
		User user = currentSession.get(User.class, loginUse);		
		return user;
	}
	
	private static final String queryDeleteUser = "delete from User where login=:setLogin";
	public void deleteUser(String login) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = 
				currentSession.createQuery(queryDeleteUser);
		theQuery.setParameter("setLogin", login);
		theQuery.executeUpdate();
		
	}

}
