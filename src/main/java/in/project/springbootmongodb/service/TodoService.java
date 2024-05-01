package in.project.springbootmongodb.service;

import java.util.List;

import in.project.springbootmongodb.exeption.TodoCollectionExeption;
import in.project.springbootmongodb.model.TodoDTO;
import jakarta.validation.ConstraintViolationException;

public interface TodoService {

	public void createTodo(TodoDTO todo) throws ConstraintViolationException , TodoCollectionExeption;
	
	public List<TodoDTO> getAllTodos();
	
	public TodoDTO getSingleTodo(String id) throws TodoCollectionExeption;
	
	public void updateTodo(String id, TodoDTO todo) throws TodoCollectionExeption;
	
	public void deleteTodoById(String id) throws TodoCollectionExeption;
	
}
