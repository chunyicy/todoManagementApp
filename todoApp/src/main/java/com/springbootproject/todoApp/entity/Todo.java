package com.springbootproject.todoApp.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity // marks the class as a JPA entity
@Table(name = "todos")
public class Todo {

    // id field is automatically generated by the database
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "todo_title", nullable = false)
    private String title;

    @Column(name = "todo_description", nullable = false)
    private String description;

    @Column(name = "todo_completed", nullable = false)
    private boolean completed;
}
