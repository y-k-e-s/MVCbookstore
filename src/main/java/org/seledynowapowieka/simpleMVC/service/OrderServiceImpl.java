package org.seledynowapowieka.simpleMVC.service;

import javax.transaction.Transactional;

import org.seledynowapowieka.simpleMVC.dao.OrderDao;
import org.seledynowapowieka.simpleMVC.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderDao orderDao;
	
	@Override
	@Transactional
	public void saveOrder(Order order) {
		orderDao.saveOrder(order);

	}
	
	@Override
	@Transactional
	public Order findOrderById(int orderId) {
		return orderDao.findOrderById(orderId);
	}

}
