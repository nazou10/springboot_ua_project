package in.project.springbootmongodb.controller;

import java.sql.Date;
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

import in.project.springbootmongodb.exeption.BookCollectionExeption ;
import in.project.springbootmongodb.model.BookDTO;
import in.project.springbootmongodb.repositary.BookRepositary;
import in.project.springbootmongodb.service.BookService;
import jakarta.validation.ConstraintViolationException;

@RestController
public class BookController {

	@Autowired
	private BookRepositary bookRepo;
	
	@Autowired
	private BookService BookService;
	
	@GetMapping("/books")
	public ResponseEntity<?> getAllbooks(){
		List<BookDTO> books = BookService.getAllBooks();
		return new ResponseEntity<>(books,books.size()> 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);

	}
	@PostMapping("/books")
	public ResponseEntity<?> createbook(@RequestBody BookDTO book){
		try {
			BookService.createBook(book);
			return new ResponseEntity<BookDTO>(book, HttpStatus.OK);
		}catch(ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}catch(BookCollectionExeption  e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
	}
 @GetMapping("/books/{id}")
 public ResponseEntity<?> getSinglebook(@PathVariable("id")String id){
	try
	{
		return new ResponseEntity<>(BookService.getSingleBook(id),HttpStatus.OK);
	}catch (Exception e) {
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
 }
 
 @PutMapping("/books/{id}")
 public ResponseEntity<?> getSinglebook(@PathVariable("id")String id,@RequestBody BookDTO book){
	try {
		BookService.updateBook(id, book);
		return new ResponseEntity<>("update book with id "+id,HttpStatus.OK);
	} catch (ConstraintViolationException e) {
		return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
	}catch (BookCollectionExeption  e) {
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);	
	}
 }
 
 @DeleteMapping("/books/{id}")
 public ResponseEntity<?> deleteById(@PathVariable("id")String id){
	try {
		 BookService.deleteBookById(id);
		 return new ResponseEntity<>("Successfully deleted with id "+id,HttpStatus.OK);
	}
	 catch(BookCollectionExeption  e)
	 {
		 return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	 }
 }
	
	
	
	
	
	
	
	
	}

