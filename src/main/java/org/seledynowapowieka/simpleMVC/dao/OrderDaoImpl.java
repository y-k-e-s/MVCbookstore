package org.seledynowapowieka.simpleMVC.dao;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.seledynowapowieka.simpleMVC.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public void saveOrder(Order order) {
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(order);
		
		
	}

	@Override
	public Order findOrderById(int orderId) {
		Session session = entityManager.unwrap(Session.class);
		
		 return session.find(Order.class, orderId);
		
	}

}
