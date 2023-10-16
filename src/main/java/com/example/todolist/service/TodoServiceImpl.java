package com.example.todolist.service;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
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
        Optional<Todo> todo = todoRepository.findById(id);

        if (todo.isPresent()) {
            return todo.get();
        }
        return null;
    }

    @Override
    public List<Todo> getAll() {
        List<Todo> todos = todoRepository.findAll();
        return todos;
    }

    @Override
    public Todo updateTodo(Long id, Todo todo) {
        Optional<Todo> updateTodo = todoRepository.findById(id);

        if (updateTodo.isPresent()) {
            updateTodo.get().setName(todo.getName());
            updateTodo.get().setDescription(todo.getDescription());

            return todoRepository.save(updateTodo.get());
        }

        return null;
    }

    @Override
    public String deleteTodo(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);

        if (todo.isPresent()) {
            todoRepository.delete(todo.get());
            return "Todo deleted successfully";
        }

        return "Todo deleted failed";
    }
}
