package tn.elastic.cms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
