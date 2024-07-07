package com.springbootproject.todoApp.dto;

// instead of returning the Todo entity directly to the client
// Data Transfer Objects is used to transfer only the required amount of data from server to client

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}
