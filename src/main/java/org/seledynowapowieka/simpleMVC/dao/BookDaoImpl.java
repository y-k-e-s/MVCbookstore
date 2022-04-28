package org.seledynowapowieka.simpleMVC.dao;


import java.util.List;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.seledynowapowieka.simpleMVC.constants.Genre;
import org.seledynowapowieka.simpleMVC.entities.Author;
import org.seledynowapowieka.simpleMVC.entities.Book;
import org.seledynowapowieka.simpleMVC.entities.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BookDaoImpl implements BookDao {
	private EntityManager entityManager;

	public BookDaoImpl() {
	}

	@Autowired
	BookDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
		System.out.println("inside BookDaoImpl constructor");
	}
	
	@Override
	public List<Book> findAll() {
		Session session = entityManager.unwrap(Session.class);
		
		
		Query<Book> theQuery = session.createQuery("from Book", Book.class);
		List<Book> books = theQuery.getResultList();
		System.out.println("fetching books...");
		return books;
	}
	
	@Override
	public Book findById(int id) {
		Session session = entityManager.unwrap(Session.class);

		Book book = session.find(Book.class, id);
		System.out.println("fetching book based on id...");
		session.close();
		return book;
	}
	
	@Override
	@Transactional
	public void deleteById(int id){

		Session session = entityManager.unwrap(Session.class);
		Query theQuery = 
				session.createQuery(
						"delete from Book where id=:bookId");
		theQuery.setParameter("bookId", id);
		
		theQuery.executeUpdate();
		
	}
	
	@Transactional
	@Override
	public void createBook(BookModel bookModel) {
		
		//////zmodyfikowac - bookModel zamiast book
		
		Session session = entityManager.unwrap(Session.class);
		Query<Author> authorsQuery = session.createQuery("from Author", Author.class);
		List<Author> authors = authorsQuery.getResultList();
		Book book = new Book();

		for(Author author : authors) {
			for(int i = 0; i < bookModel.getSecondNamesArray().size(); i++) {
				if(bookModel.getFirstNamesArray().get(i).equalsIgnoreCase(author.getFirstName())
						&& bookModel.getSecondNamesArray().get(i).equalsIgnoreCase(author.getSecondName())) {
						book.addAuthor(author);
				}
			}
		}
		
		///add business logic for scenario: some new authors already in DB, some not
		
		if(book.getAuthors().isEmpty()) {
			for(int i = 0; i < bookModel.getSecondNamesArray().size(); i++) {
				Author author = new Author(bookModel.getFirstNamesArray().get(i), 
						bookModel.getSecondNamesArray().get(i), null);
				book.addAuthor(author);
			}
		}
		/// to consider - adding genre option available for user?
		
		Query<Genre> genreQuery = session.createQuery("from Genre", Genre.class);
		List<Genre> genres = genreQuery.getResultList();
		
		System.out.println("empty or not?" + bookModel.getTitle() + " " + bookModel.getIsbn());
		
		for(Genre gen : genres) {
			if(bookModel.getGenre().equalsIgnoreCase(gen.getGenre())) {
				System.out.println("inside if(book.getGenre().getGenre().equalsIgnoreCase(genre.getGenre()))");
				book.setGenre(gen);
				}   
			} 
				if(book.getGenre() == null) {
			 	Genre genre = new Genre(bookModel.getGenre());
				book.setGenre(genre);
				}
		System.out.println("empty or not?" + bookModel.getTitle() + " " + bookModel.getIsbn());
		book.setTitle(bookModel.getTitle());
		book.setIsbn(bookModel.getIsbn());
		book.setImg(bookModel.getImg());
		session.persist(book);
	}
	
	@Transactional
	public void updateBook(Book book) {
		Session session = entityManager.unwrap(Session.class);
		
		Genre bookGenre = session.get(Genre.class, 3);
		book.setGenre(bookGenre);
		
		session.update(book);
	}
	
	@Override
	public List<Author> getAuthors() {
		Session session = entityManager.unwrap(Session.class);
		
		Query<Author> authorsQuery = session.createQuery("from Author", Author.class);
		List<Author> authors = authorsQuery.getResultList();
		System.out.println("fetching authors...");
		
		return authors;
	}
	
}
