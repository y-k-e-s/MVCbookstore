package org.seledynowapowieka.simpleMVC.entities;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="author", uniqueConstraints= {@UniqueConstraint(columnNames = {"first_name", "second_name"})})
public class Author implements Comparable<Author>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	@Column(name="first_name")
	String firstName;
	
	@Column(name="second_name")
	String secondName;
	
	@Column(name="date_of_birth")
	Date dateOfBirth;
	
	@ManyToMany(mappedBy="authors", fetch=FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	List<Book> books = new ArrayList<>();
	

	public Author(){}
	
	
	
	public Author(String firstName, String secondName, Date dateOfBirth) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.dateOfBirth = dateOfBirth;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public void addBook(Book book) {
		if(books.contains(book)) {
			return;
		}
		books.add(book);
		if(!(book==null)) {
			book.addAuthor(this);
		}
	}
	public void removeBook(Book book) {
		if(!books.contains(book)) {
			return;
		}
		books.remove(book);
		book.removeAuthor(this);
	}


	@Override
	public int compareTo(Author a) {
		
		return a.getSecondName().compareTo(getSecondName());
	}
	
	
}
