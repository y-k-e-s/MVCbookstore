package org.seledynowapowieka.simpleMVC.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "book_order")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int Id;
	
	@Column(name="delivery_status")
	String deliveryStatus;
	
	@Column(name="payment_status")
	String paymentStatus = "new";
	
	@Column(name="time_stamp")
	Timestamp timestamp;
	
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	User user;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "orders_books", 
	joinColumns = @JoinColumn(name = "order_id"), 
	inverseJoinColumns = @JoinColumn(name = "book_id"))
	List<Book> books;
	
	/*
	 *books might be unidirectional(only from order to books) 
	 * user needs to be bidirectional: from user to orders(orders history) and form order to user; order as owner
	 * add order in User and set user in order
	 * 
	 * need to implement add book method - delete only for admin purposes
	 * 
	 * in user set orphanremoval=true(orders removed on account delete)
	 * 
	 * */
	
	public Order(){
		if(books == null) {
			books = new ArrayList<Book>();
		}
		
	}
	
	

	public Order(String deliveryStatus, String paymentStatus, Timestamp timestamp) {
		super();
		this.deliveryStatus = deliveryStatus;
		this.paymentStatus = paymentStatus;
		this.timestamp = timestamp;
	}



	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	///////Books
	
	public void addBook(Book book) {
		if(!books.contains(book)) {
		books.add(book);
		}
	}
	public void removeBook(Book book) {
		if(books.contains(book)) {
		books.remove(book);
		}
	}
	
	public List<Book> getBooks() {
		return books;
	}

	
	//////User


	public void setUser(User user) {
		if(user == null) {
			this.user.removeOrder(this);
			this.user = null;
		}else {
			////ad same as before check
			if(sameAsBefore(user)) {
				return;
			}
			this.user = user;
			user.addOrder(this);
		}

	}



	public User getUser() {
		return user;
	}



	private boolean sameAsBefore(User newUser) {
		
		Boolean result = this.user == null ? newUser == null : this.user.equals(newUser);
		
		return result;
	}
}
