package com.springbootproject.todoApp.controller;

import com.springbootproject.todoApp.dto.TodoDto;
import com.springbootproject.todoApp.entity.Todo;
import com.springbootproject.todoApp.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // make this class as Spring MVC controller that handles REST API requests
@AllArgsConstructor
@RequestMapping("api/todos") // base URL path
public class TodoController {

    // constructor dependency (facilitated by Lombok's @AllArgsConstructor)
    private TodoService todoService;

    // Build Add Todo REST API
    // @RequestBody binds the HTTP request body to the todoDto parameter
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping // maps HTTP POST requests to the endpoint ("api/todos")
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){

        TodoDto savedTodo = todoService.addTodo(todoDto);

        // returns saved 'TodoDto' and status code of '201 Created'
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }


    // Build Get Todo REST API
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("{todoId}") // handles GET requests
    public ResponseEntity<TodoDto> getTodo(@PathVariable("todoId") Long todoId){ // retrieve a Todo item by it's id
        // getTodo method in todoService returns a TodoDto object representing the Todo item
        TodoDto todoDto = todoService.getTodo(todoId);
        //returns a ResponseEntity object with the todoDto as the body and HttpStatus.OK
        // (HTTP status code 200) indicating a successful response
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    // Build Get All Todos REST API
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){
        List<TodoDto> todos = todoService.getAllTodos();

        // returns a ResponseEntity with an HTTP status of 200 (OK) and
        // the body containing the list of TodoDto objects (todos)
        return ResponseEntity.ok(todos);
    }

    // Build Update Todo REST API
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("{todoId}")
    public ResponseEntity<TodoDto> updateTodo(
            @RequestBody TodoDto todoDto,  // path variable for Todo ID
            @PathVariable("todoId") Long todoId){

        // delegate update operation to the service layer
        TodoDto updatedTodo = todoService.updateTodo(todoDto, todoId);

        // return ResponseEntity with updated TodoDTO and HTTP status OK
        return ResponseEntity.ok(updatedTodo);
    }

    // Build Delete Todo REST API
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{todoId}") // maps HTTP DELETE requests to the endpoint /todos/{todoId}
    public ResponseEntity<String> deleteTodo(@PathVariable("todoId") Long todoId){
        // delegates the delete operation to the service layer
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo deleted successfully!");
    }

    // Build Complete Todo REST API
    // handles HTTP PATCH requests to mark a specific Todo resource as completed
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PatchMapping("{todoId}/complete") // maps HTTP PATCH requests to the endpoint /todos/{todoId}/complete
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("todoId") Long todoId){
        // updating the Todo entity identified by todoId and marking it as completed
        TodoDto updatedTodo = todoService.completeTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    // Build In Complete Todo REST API
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PatchMapping("{todoId}/in-complete") // maps HTTP PATCH requests to the endpoint /todos/{todoId}/in-complete
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("todoId") Long todoId){
        // delegate to the service layer to mark the Todo as incomplete
        TodoDto updatedTodo = todoService.inCompleteTodo(todoId);
        // return the updated TodoDto in the response with HTTP status 200 OK
        return ResponseEntity.ok(updatedTodo);
    }

}
