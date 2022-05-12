package by.mytest.main.dao;

import java.util.List;

import by.mytest.main.model.Order;

public interface OrderDAO {
	
	public List<Order> getOrderList();

	public void saveOrder(Order order);

	public Order getOrder(int idOrder);

	public void deleteOrder(int idOrder);

}
