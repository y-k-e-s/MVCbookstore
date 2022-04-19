package org.seledynowapowieka.simpleMVC.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.seledynowapowieka.simpleMVC.constants.Genre;
import org.seledynowapowieka.simpleMVC.validation.AuthorsNamesCheck;
import org.springframework.format.annotation.DateTimeFormat;

public class BookModel {
	
	String title;
	int isbn;
	String img;
	
	String genre;
	
	static int aNo = 1;
	
	int[] authorsNo = {1, 2, 3, 4, 5};
	
	@AuthorsNamesCheck
	List<String> namesArray;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date dateOfBirth;
	
	String bookDetails;
	
	List<String> firstNamesArray = new ArrayList<>();
	
	List<String> secondNamesArray = new ArrayList<>();


	
	public BookModel(String title, int isbn, String img, String genre, Date dateOfBirth, 
			String bookDetails, int[] authorsNo, List<String> array) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.img = img;
		this.genre = genre;
		this.dateOfBirth = dateOfBirth;
		this.bookDetails = bookDetails;
		this.namesArray = array;
		this.authorsNo = authorsNo;
		
	}


	public BookModel() {
	}

	
	

	public List<String> getFirstNamesArray() {
		return firstNamesArray;
	}


	public void setFirstNamesArray(List<String> firstNamesArray) {
		this.firstNamesArray = firstNamesArray;
	}


	public List<String> getSecondNamesArray() {
		return secondNamesArray;
	}


	public void setSecondNamesArray(List<String> secondNamesArray) {
		this.secondNamesArray = secondNamesArray;
	}



	public int[] getAuthorsNo() {
		return authorsNo;
	}


	public void setAuthorsNo(int[] authorsNo) {
		this.authorsNo = authorsNo;
	}


	public List<String> getNamesArray() {
		return namesArray;
	}


	public void setNamesArray(List<String> array) {
		this.namesArray = array;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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

	public String getGenre() {
		return genre;
	}

	public String getBookDetails() {
		return bookDetails;
	}

	public void setBookDetails(String bookDetails) {
		this.bookDetails = bookDetails;
	}
	
	public Map<String, String> getGenres(){
		
		Map<String, String> genres = new TreeMap<>();
		
		genres.put("Philosophy", Genre.PHILOSOPHY);
		genres.put("Fiction", Genre.FICTION);
		genres.put("Non Fiction", Genre.NON_FICTION);
		genres.put("Poetry", Genre.POETRY);
		genres.put("Drama", Genre.DRAMA);
		genres.put("IT", Genre.IT);

		return genres;
	}


	public void buildNames() {
		
		if(namesArray != null) {
		for(int i = 0; i < namesArray.size(); i++) {
			if(i%2 == 0) {
				System.out.println("firstNamesArray i :" + i);
				firstNamesArray.add(namesArray.get(i));
			}else {
				System.out.println("secondNamesArray i :" + i);
				secondNamesArray.add(namesArray.get(i));
			}
		}
		}else {
			System.out.println("stringArray null!!!");
		}
		System.out.println("first names: " + firstNamesArray);
		System.out.println("second names: " + secondNamesArray);
	}
}
