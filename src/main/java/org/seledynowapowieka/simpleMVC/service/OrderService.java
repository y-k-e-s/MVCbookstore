package org.seledynowapowieka.simpleMVC.service;

import org.seledynowapowieka.simpleMVC.entities.Order;

public interface OrderService {
	public void saveOrder(Order order);
	public Order findOrderById(int orderId);
}
