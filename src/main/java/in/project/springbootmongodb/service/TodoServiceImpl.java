package in.project.springbootmongodb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import in.project.springbootmongodb.exeption.TodoCollectionExeption;
import in.project.springbootmongodb.model.TodoDTO;
import in.project.springbootmongodb.repositary.TodoRepositary;
import jakarta.validation.ConstraintViolationException;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepositary todoRepo;
	
	@Override
	public void createTodo(TodoDTO todo) throws ConstraintViolationException , TodoCollectionExeption{
	  Optional<TodoDTO> todoOptional =	todoRepo.findByTodo(todo.getTodo());
		if(todoOptional.isPresent())
		{
			throw new TodoCollectionExeption(TodoCollectionExeption.TodoAlreadyExists());
		}
		else {
			todo.setCreatedAt(new Date(System.currentTimeMillis()));
			todoRepo.save(todo);
		}
	}

	@Override
	public List<TodoDTO> getAllTodos() {
		List<TodoDTO> todos=todoRepo.findAll();
		if(todos.size()>0)
		{
			return todos;
		}
		else
		{
			return new ArrayList<TodoDTO>();
		}
	}

	@Override
	public TodoDTO getSingleTodo(String id) throws TodoCollectionExeption {
		Optional<TodoDTO> optionalTodo= todoRepo.findById(id);
		if(optionalTodo.isPresent())
		{
			throw new TodoCollectionExeption(TodoCollectionExeption.NotFoundException(id));
		}
		else
			{
			return optionalTodo.get();					
		}
			
	}

	@Override
	public void updateTodo(String id, TodoDTO todo) throws TodoCollectionExeption {
		Optional<TodoDTO> todoWithId=todoRepo.findById(id);
		Optional<TodoDTO> todoWithSameName=todoRepo.findByTodo(todo.getTodo());
		
		if(todoWithId.isPresent())
		{
			
			
			
			TodoDTO todoToUpdate= todoWithId.get();
			
			todoToUpdate.setTodo(todo.getTodo());
			todoToUpdate.setDescription(todo.getDescription());
			todoToUpdate.setCompleted(todo.getCompleted());
			todoToUpdate.setUpdatedAt(new java.util.Date(System.currentTimeMillis()));
			todoRepo.save(todoToUpdate);
		}
		else {
			{
				throw new TodoCollectionExeption(TodoCollectionExeption.NotFoundException(id));
			}
		}
	}

	@Override
	public void deleteTodoById(String id) throws TodoCollectionExeption {
		Optional<TodoDTO> todOptional= todoRepo.findById(id);
		if(!todOptional.isPresent())
		{
			throw new TodoCollectionExeption(TodoCollectionExeption.NotFoundException(id));
			
		}
		else {
			{
				todoRepo.deleteById(id);
			}
		}
	}

	
}
