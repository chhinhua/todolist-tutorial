package com.example.todolist.service;

import com.example.todolist.entity.Todo;
import com.example.todolist.exception.ResourceNotFoundException;
import com.example.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ResourceClosedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;

    @Override
    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Todo getOne(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", id));
        return todo;
    }

    @Override
    public List<Todo> getAll() {
        List<Todo> todos = todoRepository.findAll();
        return todos;
    }

    @Override
    public Todo updateTodo(Long id, Todo todo) {
        Todo updateTodo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", id));

        updateTodo.setName(todo.getName());
        updateTodo.setDescription(todo.getDescription());

        return todoRepository.save(updateTodo);
    }

    @Override
    public String deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", id));

        todoRepository.delete(todo);

        return "Todo deleted successfully";
    }
}
