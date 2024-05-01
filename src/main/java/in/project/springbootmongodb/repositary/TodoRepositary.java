package in.project.springbootmongodb.repositary;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import in.project.springbootmongodb.model.TodoDTO;
@Repository
public interface TodoRepositary extends MongoRepository<TodoDTO,String> {

	@Query("{'todo' : ?0}")
	

	Optional<TodoDTO> findByTodo(String todo);
	
}
