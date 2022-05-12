package by.mytest.main.service;

import java.util.List;

import by.mytest.main.model.Order;

public interface OrderService {
	
	List<Order> getOrder();
	void saveOrder(Order order);
	Order getOrder(int idOrder);
	void deleteOrder (int idOrder);

}
