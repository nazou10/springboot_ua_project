package in.project.springbootmongodb.service;

import java.util.List;

import in.project.springbootmongodb.exeption.BookCollectionExeption;
import in.project.springbootmongodb.model.BookDTO;
import jakarta.validation.ConstraintViolationException;

public interface BookService {

	public void createBook(BookDTO Book) throws ConstraintViolationException , BookCollectionExeption;
	
	public List<BookDTO> getAllBooks();
	
	public BookDTO getSingleBook(String id) throws BookCollectionExeption;
	
	public void updateBook(String id, BookDTO Book) throws BookCollectionExeption;
	
	public void deleteBookById(String id) throws BookCollectionExeption;
	
}
