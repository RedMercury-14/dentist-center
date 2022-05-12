package by.mytest.main.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.mytest.main.dao.OrderDAO;
import by.mytest.main.model.Order;
@Repository
public class OrderDAOImpl implements OrderDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	private static final String queryGetList = "from Order order by idOrder";
	@Override
	public List<Order> getOrderList() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Order> theOrder = currentSession.createQuery(queryGetList, Order.class);
		List <Order> orders = theOrder.getResultList();
		return orders;
	}

	@Override
	public void saveOrder(Order order) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(order);
		
	}

	@Override
	public Order getOrder(int idOrder) {
		Session currentSession = sessionFactory.getCurrentSession();
		Order order = currentSession.get(Order.class, idOrder);
		return order;
	}

	private static final String queryDeleteOrder = "delete from Order where idOrder=:setId";
	@Override
	public void deleteOrder(int idOrder) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = 
				currentSession.createQuery(queryDeleteOrder);
		theQuery.setParameter("setId", idOrder);
		theQuery.executeUpdate();		
	}

}
