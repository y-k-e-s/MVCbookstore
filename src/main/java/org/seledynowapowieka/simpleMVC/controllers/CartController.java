package org.seledynowapowieka.simpleMVC.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.seledynowapowieka.simpleMVC.entities.Book;
import org.seledynowapowieka.simpleMVC.entities.Order;
import org.seledynowapowieka.simpleMVC.entities.User;
import org.seledynowapowieka.simpleMVC.service.BookService;
import org.seledynowapowieka.simpleMVC.service.OrderService;
import org.seledynowapowieka.simpleMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrderService orderService;

	@Autowired
	public CartController(BookService bookService){
		this.bookService = bookService;
	}
	
	////// buy
	
	@RequestMapping("/addToCart")
	public String addToCart(@RequestParam("bookId") int bookId, @RequestParam("user") String userName, 
			HttpServletRequest request) {

		
		HttpSession session = request.getSession();
		Order order;
		Book book = bookService.findById(bookId);
		User user = userService.findByUserName(userName);
		
		
		if(session.getAttribute("order") == null) {
			System.out.println("inside session.getAttribute(\"order\") == null");
			order = new Order();
			order.addBook(book);
			order.setUser(user);
			session.setAttribute("order", order);
			orderService.saveOrder(order);
		}else {
			System.out.println("inside else...");
			Order tempOrder = (Order)session.getAttribute("order");
			int orderId = tempOrder.getId();
			
			order = orderService.findOrderById(orderId);
			order.addBook(book);
			
			orderService.saveOrder(order);
			session.setAttribute("order", order);
		}
		
		for(Book bk : order.getBooks()) {
			System.out.print(bk.getTitle() + " | ");
		}
		
		
		return "redirect:/";
	} 
	
	@RequestMapping("/showCart")
	public String showCart() {
		
		return "myCart";
	}
	
	@RequestMapping("/removeFromCart")
	public String removeFromCart(@RequestParam("bookId") int bookId, @RequestParam("orderId") int orderId, HttpServletRequest request) {
		
		Book book = bookService.findById(bookId);
		Order order = orderService.findOrderById(orderId);
		HttpSession session = request.getSession();
		order.removeBook(book);
		orderService.saveOrder(order);
		session.setAttribute("order", order);
		
		return "myCart";
	}
	
	@RequestMapping("/buy")
	public String buyBook(@RequestParam("orderId") int orderId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		Order order = orderService.findOrderById(orderId);
		String paymentstatus = "paid";
		order.setPaymentStatus(paymentstatus);
		orderService.saveOrder(order);
		
		session.setAttribute("order", null);
		
		return "redirect:/";
	}
}
