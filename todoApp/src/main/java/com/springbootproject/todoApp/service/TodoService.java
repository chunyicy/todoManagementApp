package com.springbootproject.todoApp.service;

import com.springbootproject.todoApp.dto.TodoDto;
import com.springbootproject.todoApp.entity.Todo;

import java.util.List;

// define methods for performing business logic operations
public interface TodoService {
    TodoDto addTodo(TodoDto TodoDto);

    TodoDto getTodo(Long id);
    List<TodoDto> getAllTodos();

    TodoDto updateTodo(TodoDto todoDto, Long id);

    void deleteTodo(Long id);

    TodoDto completeTodo(Long id);

    TodoDto inCompleteTodo(Long id);

}
