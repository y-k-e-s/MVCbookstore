package org.seledynowapowieka.simpleMVC.constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.seledynowapowieka.simpleMVC.entities.Book;

@Entity
@Table(name="book_genre")
public class Genre implements Comparable<Genre>{
	public static final String PHILOSOPHY = "philosophy";
	public static final String FICTION = "fiction";
	public static final String NON_FICTION = "non fiction";
	public static final String POETRY = "poetry";
	public static final String DRAMA = "drama";
	public static final String IT = "IT";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	String genre;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="genre", cascade=CascadeType.ALL ,orphanRemoval=true)
	List<Book> books = new ArrayList<>();


	
	public Genre(){

	}

	public Genre(String genre) {
		super();
		this.genre = genre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	///books///
	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
		if(!(books == null)) {
			for(Book book : books) {
				book.setGenre(this);
			}
		}
	}

	public void addBook(Book book) {
		
		if(books.contains(book)) {
			System.out.println("inside books.contains(book)");
			return;
		}
		books.add(book);
		System.out.println("books.add(book);");
		
		
		if(!(book == null)) {
			System.out.println("inside !(book == null)");
			book.setGenre(this);
		}
		
	}
	
	public void removeBook(Book book) {
		if(!books.contains(book)) {
			System.out.println("inside if(!books.contains(book))");
			return;
		}
		books.remove(book);
		System.out.println("books.remove(book);");
		
		if(!(book == null)) {
			System.out.println("inside if(!(book == null)");
			book.setGenre(null);
		}
		
	}

	@Override
	public int compareTo(Genre o) {
		return genre.compareTo(o.getGenre());
	}
}
