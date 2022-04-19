import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.seledynowapowieka.simpleMVC.constants.Genre;
import org.seledynowapowieka.simpleMVC.entities.Author;
import org.seledynowapowieka.simpleMVC.entities.Book;
import org.seledynowapowieka.simpleMVC.entities.BookDetail;

public class test {
	/*
	
	public static void main(String[]args) {
		Author author1 = new Author("Martyna", "Kadela", null);
		Author author2 = new Author("Dyzio", "Dyziowicz", null);
		Author author3 = new Author("Blues", "Bluesowicz", null);
		Author author4 = new Author("Pinia", "Piniowiczuwna", null);
		Author author5 = new Author("Bruno", "Brunowicz", null);

		TreeSet<Author> authors = new TreeSet<>();
		authors.add(author5);
		authors.add(author4);
		authors.add(author3);
		
		Genre drama = new Genre();
		drama.setGenre(Genre.DRAMA);
		
		BookDetail bookDetail1 = new BookDetail("quite average I must say", 3.6d);
		BookDetail bookDetail2 = new BookDetail("splendid book", 4.9d);
		
		Genre genre1 = new Genre(Genre.NON_FICTION);
		Genre genre2 = new Genre(Genre.POETRY);
		
		Book book = new Book("Book of Books", 1234, drama, bookDetail1, authors, "img");

		
		
	}
	
	
	
	
	public static void main(String[]args) {
		test test = new test();
		
		Author author1 = new Author("Blues", "Cadela", null);
		Author author2 = new Author("Blues", "Ayziowicz", null);
		Author author3 = new Author("Blues", "Bluesowicz", null);
		List<Author> authors = new ArrayList<Author>();
		
		authors.add(author3);
		authors.add(author2);
		authors.add(author1);  
		authors.add(author3);
		authors.add(author2);
		authors.add(author1); 
		authors.sort(test.new Comp());
		
		Set<Author> aut = new HashSet<Author>(authors);
		authors.clear();
		authors.addAll(aut);
		
		for(Author author : authors) {
			System.out.println(author.getFirstName() + " " + author.getSecondName());
		}
		
	}
	
	public class Comp implements Comparator<Author>{
		
		Comp(){}
		
		@Override
		public int compare(Author a1, Author a2) {
			
			int firstName = a1.getFirstName().compareTo(a2.getFirstName());
			int secondName = a1.getSecondName().compareTo(a2.getSecondName());
			
			return (firstName == 0) ? secondName : firstName;
		}
		
	}
	*/
}
