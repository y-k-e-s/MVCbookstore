package org.seledynowapowieka.simpleMVC.dao;

import org.seledynowapowieka.simpleMVC.entities.Order;

public interface OrderDao {
	public void saveOrder(Order order);

	public Order findOrderById(int orderId);
}
