package com.learning.junitandmockito.data.api;

import java.util.List;

public interface TodoService {
    public List<String> retrieveTodos(String user);
    public void deleteTodo(String todo);
}