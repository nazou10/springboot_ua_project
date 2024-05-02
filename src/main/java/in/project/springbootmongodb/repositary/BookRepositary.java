package in.project.springbootmongodb.repositary;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import in.project.springbootmongodb.model.BookDTO;
@Repository
public interface BookRepositary extends MongoRepository<BookDTO,String> {

	@Query("{'Book' : ?0}")
	

	Optional<BookDTO> findByBook(String Book);
	
}
