package by.mytest.main.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.mytest.main.dao.RoleDAO;
import by.mytest.main.model.Role;

@Repository
public class RoleDAOImpl implements RoleDAO{
	@Autowired
	private SessionFactory sessionFactory;

	private static final String queryGetList = "from Role order by id";
	public List<Role> getRoleList() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Role> theRole = currentSession.createQuery(queryGetList, Role.class);
		List <Role> roles = theRole.getResultList();
		return roles;
	}

	public void saveRole(Role role) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(role);
		
	}

	public Role getRole(int idRole) {
		Session currentSession = sessionFactory.getCurrentSession();
		Role role = currentSession.get(Role.class, idRole);		
		return role;
	}

	private static final String queryDeleteRole = "delete from Role where id=:roleId";
	public void deleteRole(int idRole) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = 
				currentSession.createQuery(queryDeleteRole);
		theQuery.setParameter("roleId", idRole);
		theQuery.executeUpdate();
		
	}

}
