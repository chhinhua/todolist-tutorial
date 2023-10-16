package com.example.todolist.service;

import com.example.todolist.entity.Todo;

import java.util.List;

public interface TodoService {
    Todo addTodo(final Todo todo);

    Todo getOne(final Long id);

    List<Todo> getAll();

    Todo updateTodo(final Long id, final Todo todo);

    String deleteTodo(final Long id);
}
