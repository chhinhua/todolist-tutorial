package com.example.todolist.controller;

import com.example.todolist.entity.Todo;
import com.example.todolist.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.spi.ResolveNaturalIdEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private final TodoService todoService;

    /**
     * Add new Todo
     * @param todo
     * @return todo
     */
    @PostMapping
    public ResponseEntity<Todo> addTodo(@Valid @RequestBody Todo todo) {
        Todo saveTodo = todoService.addTodo(todo);
        return new ResponseEntity<>(saveTodo, HttpStatus.CREATED);
    }

    /**
     * Get the single Todo by id
     * @param todoId
     * @return todo
     */
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getOne(@PathVariable(value = "id") Long todoId) {
        Todo todo = todoService.getOne(todoId);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    /**
     * Get all todo
     * @return list todo
     */
    @GetMapping
    public ResponseEntity<List<Todo>> getAll() {
        List<Todo> todos = todoService.getAll();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    /**
     * Update todo
     * @param todoId
     * @param todo
     * @return todo have been updated if successful or null if failed
     */
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@Valid @RequestBody Todo todo,
                                           @PathVariable(value = "id") Long todoId
                                           ) {
        Todo updateTodo = todoService.updateTodo(todoId, todo);

        return ResponseEntity.ok(updateTodo);
    }

    /**
     * Delete todo by id
     * @param todoId
     * @return result message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable(value = "id") Long todoId) {
        String result = todoService.deleteTodo(todoId);
        return ResponseEntity.ok(result);
    }
}
