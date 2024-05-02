package in.project.springbootmongodb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import in.project.springbootmongodb.exeption.BookCollectionExeption;
import in.project.springbootmongodb.model.BookDTO;
import in.project.springbootmongodb.repositary.BookRepositary;
import jakarta.validation.ConstraintViolationException;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepositary BookRepo;
	
	@Override
	public void createBook(BookDTO Book) throws ConstraintViolationException , BookCollectionExeption{
	  Optional<BookDTO> BookOptional =	BookRepo.findByBook(Book.getBook());
		if(BookOptional.isPresent())
		{
			throw new BookCollectionExeption(BookCollectionExeption.BookAlreadyExists());
		}
		else {
			Book.setCreatedAt(new Date(System.currentTimeMillis()));
			BookRepo.save(Book);
		}
	}

	@Override
	public List<BookDTO> getAllBooks() {
		List<BookDTO> Books=BookRepo.findAll();
		if(Books.size()>0)
		{
			return Books;
		}
		else
		{
			return new ArrayList<BookDTO>();
		}
	}

	@Override
	public BookDTO getSingleBook(String id) throws BookCollectionExeption {
		Optional<BookDTO> optionalBook= BookRepo.findById(id);
		if(optionalBook.isPresent())
		{
			return optionalBook.get();	
		}
		else
			{
				throw new BookCollectionExeption(BookCollectionExeption.NotFoundException(id));
		}
			
	}

	@Override
	public void updateBook(String id, BookDTO Book) throws BookCollectionExeption {
		Optional<BookDTO> BookWithId=BookRepo.findById(id);
		Optional<BookDTO> BookWithSameName=BookRepo.findByBook(Book.getBook());
		
		if(BookWithId.isPresent())
		{
			
			
			
			BookDTO BookToUpdate= BookWithId.get();
			
			BookToUpdate.setBook(Book.getBook());
			BookToUpdate.setDescription(Book.getDescription());
			BookToUpdate.setavailibility(Book.getavailibility());
			BookToUpdate.setUpdatedAt(new java.util.Date(System.currentTimeMillis()));
			BookRepo.save(BookToUpdate);
		}
		else {
			{
				throw new BookCollectionExeption(BookCollectionExeption.NotFoundException(id));
			}
		}
	}

	@Override
	public void deleteBookById(String id) throws BookCollectionExeption {
		Optional<BookDTO> Bookptional= BookRepo.findById(id);
		if(!Bookptional.isPresent())
		{
			throw new BookCollectionExeption(BookCollectionExeption.NotFoundException(id));
			
		}
		else {
			{
				BookRepo.deleteById(id);
			}
		}
	}

	
}
