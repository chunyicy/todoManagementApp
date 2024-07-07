package com.springbootproject.todoApp.service;


import com.springbootproject.todoApp.dto.TodoDto;
import com.springbootproject.todoApp.entity.Todo;
import com.springbootproject.todoApp.exception.ResourceNotFoundException;
import com.springbootproject.todoApp.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // indicates this class is a Spring service bean
@AllArgsConstructor // generate parameterized constructor
public class TodoServiceImpl implements TodoService{

    // TodoRepository as a dependency, it provides methods to interact with the database to perform
    // CRUD operation on 'Todo' entities. Constructor based dependency injection.
    private TodoRepository todoRepository;

    //  model mapper object dependency injected via constructor injection
    private ModelMapper modelMapper;
    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        // convert TodoDto into Todo JPA entity
        Todo todo = modelMapper.map(todoDto, Todo.class);

        // save Todo Jpa entity into MySQL database
        Todo savedTodo = todoRepository.save(todo);

        // convert saved Todo Jpa entity object into TodoDto object
        // convert saved 'Todo' entity back into a 'TodoDto' object
        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);

        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long todoId) {

        // findById returns an Optional<Todo>, .get() retrieves the actual Todo object if it exists
        Todo todo = todoRepository.findById(todoId).orElseThrow(() ->
                new ResourceNotFoundException("Todo not found with id " + todoId));

        // maps it to a DTO (TodoDto) using ModelMapper
        return modelMapper.map(todo, TodoDto.class);
    }


    // retrieve all Todo entities from the database and maps them to Dto (Data Transfer Objects)
    @Override
    public List<TodoDto> getAllTodos() { // return list of TodoDto objects

        // findAll() method retrieves all Todo entities stored in the database and returns them as a List
        List<Todo> todos = todoRepository.findAll();

        // converts each Todo entity into a TodoDto object using modelMapper
        return todos.stream().map((todo) ->
                modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {

        // retrieve existing Todo entity from the repository based on id
        // uses todoRepository to find a Todo entity by its id

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));

        // update the properties of the retrieved Todo entity with values from todoDto
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        // dave the updated Todo entity back to the repository
        Todo updatedTodo = todoRepository.save(todo);

        // map the updated Todo entity to TodoDto and return it
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    // delete specific Todo entity from the repository based on its 'todoId'
    @Override
    public void deleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new ResourceNotFoundException("Todo not found with id: " + todoId)
        );

        todoRepository.deleteById(todoId);
    }

    @Override
    public TodoDto completeTodo(Long todoId) {
        // retrieve the Todo entity from the repository based on todoId
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                ()-> new ResourceNotFoundException("Todo not found with id: " + todoId)
        );
        // set the completed flag to true
        todo.setCompleted(Boolean.TRUE);
        // save the updated Todo entity back to the repository
        Todo updatedTodo = todoRepository.save(todo);
        // map the updated Todo entity to TodoDto and return it
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long todoId) {
        // retrieve the Todo entity from the repository based on todoId
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                ()-> new ResourceNotFoundException("Todo not found with id: " + todoId)
        );
        // set the completed flag to false
        todo.setCompleted(Boolean.FALSE);
        // save the updated Todo entity back to the repository
        Todo updatedTodo = todoRepository.save(todo);
        // map the updated Todo entity to TodoDto and return it
        return modelMapper.map(updatedTodo, TodoDto.class);
    }
}
