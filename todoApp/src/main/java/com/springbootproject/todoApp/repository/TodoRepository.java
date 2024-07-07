package com.springbootproject.todoApp.repository;

import com.springbootproject.todoApp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

// repository interfaces  are used to interact with the database
// by extending JpaRepository <Todo, Long>, the TodoRepository interface will
// get a CRUD methods to perform common CRUD database operations on the Todo entity
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
