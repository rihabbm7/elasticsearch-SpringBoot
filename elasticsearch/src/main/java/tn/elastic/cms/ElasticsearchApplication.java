package tn.elastic.cms;

import java.util.Map;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import tn.elastic.cms.model.Book;
import tn.elastic.cms.services.BookService;

@SpringBootApplication
public class ElasticsearchApplication implements CommandLineRunner{
	@Autowired
    private ElasticsearchOperations es;

    @Autowired
    private BookService bookService;
	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchApplication.class, args);
	}

	public void run(String... args) throws Exception {
	

	        bookService.save(new Book("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017"));
	        bookService.save(new Book("1002", "Apache Lucene Basics", "Rambabu Posa", "13-MAR-2017"));
	        bookService.save(new Book("1003", "Apache Solr Basics", "Rambabu Posa", "21-MAR-2017"));

	        //fuzzey search
	        Page<Book> books = bookService.findByAuthor("Rambabu",PageRequest.of(0, 10));

	        //List<Book> books = bookService.findByTitle("Elasticsearch Basics");

	        books.forEach(x -> System.out.println(x));

		
	}


}
