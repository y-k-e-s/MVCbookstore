package org.seledynowapowieka.simpleMVC.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.seledynowapowieka.simpleMVC.constants.Genre;
import org.seledynowapowieka.simpleMVC.controllers.MainController;
import org.seledynowapowieka.simpleMVC.service.BookService;
import org.seledynowapowieka.simpleMVC.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "book")
public class Book{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	String title;

	String img;

	int isbn;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name = "genre_id")
	Genre genre;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "book_detail_id")
	BookDetail bookDetail;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.PERSIST })
	@JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
	List<Author> authors = new ArrayList<>();

	public Book() {
	}

	public Book(String title, int isbn, String img) {
		super();
		this.title = title;
		this.img = img;
		this.isbn = isbn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	///// Authors/////

	public List<Author> getAuthors() {
		return authors;
	}

	public void addAuthor(Author author) {
		if (authors.contains(author)) {
			return;
		}

		authors.add(author);
		if (!(author == null)) {
			author.addBook(this);
		}
	} 

	public void removeAuthor(Author author) {
		if (!authors.contains(author)) {
			return;
		}
		authors.remove(author);
		if (!(author == null)) {
			author.removeBook(this);
		}
	}

	public void setAuthors(List<Author> authors) {
		if (!(this.authors == null)) {
			for (Author aut : this.authors) {
				aut.removeBook(this);
			}
		}

		this.authors = authors;
		if (!(authors == null)) {
			for (Author aut : authors) {
				aut.addBook(this);
			}
		}
	}

	/// Book Details///

	public BookDetail getBookDetail() {
		return bookDetail;
	}

	public void setBookDetail(BookDetail bookDetail) {
		if (sameAsBefore(bookDetail)) {
			return;
		}

		BookDetail oldBookDetail = this.bookDetail;
		this.bookDetail = bookDetail;

		if (!(oldBookDetail == null)) {
			oldBookDetail.setBook(null);
		}

		if (!(bookDetail == null)) {
			bookDetail.setBook(this);
		}
	}

	private boolean sameAsBefore(BookDetail newBookDetail) {
		return bookDetail == null ? newBookDetail == null : bookDetail.equals(newBookDetail);
	}
 
	/// Genre///

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre newGenre) {
		if (sameAsBefore(newGenre)) {
			System.out.println("inside sameAsBefore");
			return;
		}
		
		if(this.genre != null) {
			this.genre.removeBook(this);
		}
		
		this.genre = newGenre;
		
		if(newGenre != null) {
			newGenre.addBook(this);
		}

	}
	


	private boolean sameAsBefore(Genre genre) {
		return (genre == null) ? this.genre == null : this.genre == genre;
	}
	
}
