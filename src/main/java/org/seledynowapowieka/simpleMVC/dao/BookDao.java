package org.seledynowapowieka.simpleMVC.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.TreeSet;

import org.seledynowapowieka.simpleMVC.constants.Genre;
import org.seledynowapowieka.simpleMVC.entities.Author;
import org.seledynowapowieka.simpleMVC.entities.Book;
import org.seledynowapowieka.simpleMVC.entities.BookModel;

public interface BookDao {
	List<Book> findAll();
	Book findById(int id);
	void deleteById(int id);
	void createBook(BookModel bookModel);
	void updateBook(Book book);
	List<Author> getAuthors();
}
