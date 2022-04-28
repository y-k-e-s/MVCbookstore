package org.seledynowapowieka.simpleMVC.controllers;


import java.util.List;

import javax.validation.Valid;
import org.seledynowapowieka.simpleMVC.entities.Book;
import org.seledynowapowieka.simpleMVC.entities.BookModel;
import org.seledynowapowieka.simpleMVC.service.BookService;
import org.seledynowapowieka.simpleMVC.service.OrderService;
import org.seledynowapowieka.simpleMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	//HttpSession session;

	static BookService bookService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrderService orderService;
	
	public MainController() {}

	@Autowired
	public MainController(BookService bookService){
		MainController.bookService = bookService;
	}
	@RequestMapping("/")
	public ModelAndView homePage() {
		
		List<Book> books = bookService.findAll();
		ModelAndView model = new ModelAndView("homepage", "books", books);

		return model;
	}

	@RequestMapping("/addBook")
	public String addBookForm(BookModel bookModel, Model theModel) {
	
		return "addBookForm";
	}
	
	
	@RequestMapping(value="/saveBook", method={RequestMethod.POST})
	public String bookAdded(@Valid BookModel bookModel, BindingResult br) {
		
		if(br.hasErrors()) {
			bookModel.getNamesArray().clear();
			System.out.println("inside br.hasErrors()");
			return "addBookForm";
		}
		bookModel.buildNames();
		bookService.createBook(bookModel);
		System.out.println("outside br.hasErrors()");
		return "redirect:/";
	}
	
	
	////// delete
	
	@RequestMapping("/delete")
	public String deleteBook(@RequestParam("bookId") int bookId) {
		
		System.out.println("bookId: " + bookId);
		bookService.deleteById(bookId);
		
		return "redirect:/";
	} 
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void go() {
		/*
		Book book = bookService.findById(2);
		bookService.updateBook(book);
		
		
		Book book1 = new Book("Revange of Reason", 4321, 
				"https://images-na.ssl-images-amazon.com/images/I/610q2b0CZeS._SX350_BO1,204,203,200_.jpg");
		Book book2 = new Book("From Realism to Realicism", 4321, 
				"https://images-na.ssl-images-amazon.com/images/I/51Tohx0wDnL._SX342_SY445_QL70_FMwebp_.jpg");
		Book book3 = new Book("Peirce Scholastic Realism", 4321, 
				"https://images-na.ssl-images-amazon.com/images/I/41x8kr3mx0L._SX331_BO1,204,203,200_.jpg");
		Book book4 = new Book("Machine Learning Math", 4321, 
				"https://m.media-amazon.com/images/I/515gX5+Mn3L._SY346_.jpg");
		Book book5 = new Book("Deep Learning", 4321, 
				"https://images-na.ssl-images-amazon.com/images/I/51txriR2Q5L._SX330_BO1,204,203,200_.jpg");
		
		
		BookDetail bookDetail1 = new BookDetail("fun to read!", 4.5);
		BookDetail bookDetail2 = new BookDetail("very good", 4.1);
		BookDetail bookDetail3 = new BookDetail("not recommended", 2.5);
		BookDetail bookDetail4 = new BookDetail("average", 3.5);
		BookDetail bookDetail5 = new BookDetail("splendid", 4.9);
		 
		
		Author author1 = new Author("Dyzio", "Dyziowicz", null);
		Author author2 = new Author("Blues", "Bluesowicz", null);
		Author author3 = new Author("Maciej", "Olszowski", null);
		Author author4 = new Author("Martynka", "Kadela", null);
		
		book1.setBookDetail(bookDetail1);
		book1.addAuthor(author2);
		book1.addAuthor(author1);
		
		bookService.createBook(book1, Genre.PHILOSOPHY); 
		
		book2.setBookDetail(bookDetail2);
		book2.addAuthor(author3);
		
		bookService.createBook(book2, Genre.PHILOSOPHY); 
		
		book3.setBookDetail(bookDetail3);
		book3.addAuthor(author1);
		
		bookService.createBook(book3, Genre.PHILOSOPHY); 
		
		book4.setBookDetail(bookDetail4);
		book4.addAuthor(author4);
		
		bookService.createBook(book4, Genre.IT); 
		
		book5.setBookDetail(bookDetail5);
		book5.addAuthor(author4);
		
		bookService.createBook(book5, Genre.IT); 
		*/
	}
}
