package org.seledynowapowieka.simpleMVC.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="book_detail")
public class BookDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	String description;
	
	double rating;
	
	@OneToOne(mappedBy="bookDetail", cascade = CascadeType.ALL)
	Book book;
	
	public BookDetail() {}
	
	
	
	public BookDetail(String description, double rating) {
		super();
		this.description = description;
		this.rating = rating;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	
	public void setBook(Book book) {
		if(sameAsBefore(book)) {
			return;
		}
		
		Book oldBook = this.book;
		this.book = book;
		
		if(!(oldBook == null)) {
			oldBook.setBookDetail(null);
		}
		
		if(!(book == null)) {
			book.setBookDetail(this);
		}
	}
	
	public Book getBook() {
		return book;
	}
	
	private boolean sameAsBefore(Book newBook) {
		return book == null ? newBook == null : book.equals(newBook);
	}
	
}
