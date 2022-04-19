package org.seledynowapowieka.simpleMVC.service;

import java.sql.SQLException;
import java.util.List;
import java.util.TreeSet;

import org.seledynowapowieka.simpleMVC.constants.Genre;
import org.seledynowapowieka.simpleMVC.dao.BookDao;
import org.seledynowapowieka.simpleMVC.dao.BookDaoImpl;
import org.seledynowapowieka.simpleMVC.entities.Author;
import org.seledynowapowieka.simpleMVC.entities.Book;
import org.seledynowapowieka.simpleMVC.entities.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService{
	@Autowired 
	BookDao bookDao;
	
	public BookServiceImpl(){}
	
	
	BookServiceImpl(BookDao bookDao){
		this.bookDao = bookDao;
	}
	
	
	@Override
	public List<Book> findAll() {
		return bookDao.findAll();
	}

	@Override
	public Book findById(int id) {
		return bookDao.findById(id);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
	
			bookDao.deleteById(id);
		
	}

	@Override
	public void createBook(BookModel bookModel) {
		bookDao.createBook(bookModel);
		
	}
	
	public void updateBook(Book book) {
		bookDao.updateBook(book);
	}

	@Override
	public List<Author> getAuthors() {
		
		return bookDao.getAuthors();
	}
}
