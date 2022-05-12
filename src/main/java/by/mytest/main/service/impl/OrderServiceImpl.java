package by.mytest.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.mytest.main.dao.OrderDAO;
import by.mytest.main.model.Order;
import by.mytest.main.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderDAO orderDAO;

	@Override
	@Transactional
	public List<Order> getOrder() {
		return orderDAO.getOrderList();
	}

	@Override
	@Transactional
	public void saveOrder(Order order) {
		orderDAO.saveOrder(order);
		
	}

	@Override
	@Transactional
	public Order getOrder(int idOrder) {
		return orderDAO.getOrder(idOrder);
	}

	@Override
	@Transactional
	public void deleteOrder(int idOrder) {
		orderDAO.deleteOrder(idOrder);
		
	}

}
