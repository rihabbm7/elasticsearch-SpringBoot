package tn.elastic.cms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.elastic.cms.model.Book;
import tn.elastic.cms.repository.BookRepository;

@RestController
public class BookRestController {
	@Autowired
	private BookRepository bookRepository;

	@GetMapping("/Books")
	public Iterable<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	@GetMapping("/book/{id}")
	public Optional<Book> findBook(@RequestBody String id){
		return bookRepository.findById(id);
	}
	
	@PostMapping("/saveBook") public Book saveBook(@RequestBody Book book) {
		bookRepository.save(book);
		return book;
	}
	@DeleteMapping("/removeBook/{title}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable("title") String title){
		 try {
			 bookRepository.deleteByTitleContains(title);
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    } catch (Exception e) {
		      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	@PutMapping("updateBook/{id}")
	public ResponseEntity<Book> update(@RequestBody Book book,@PathVariable("id") String id){
		Optional<Book> data =bookRepository.findById(id);
		if (data.isPresent()) {
		      Book _book = data.get();
		      _book.setTitle(book.getTitle());
		      _book.setAuthor(book.getAuthor());
		      _book.setReleaseDate(book.getReleaseDate());
		      return new ResponseEntity<Book>(bookRepository.save(_book), HttpStatus.OK);
		    } 
		else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		
	}
}
