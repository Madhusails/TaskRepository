package com.learning.junitandmockito.business;

import com.learning.junitandmockito.data.api.TodoService;
import com.learning.junitandmockito.data.api.TodoServiceStub;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoBusinessImplStubTest {
    @Test
    public void usingAStub() {
        TodoService todoService = new TodoServiceStub();
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("dummy");
        assertEquals(2, todos.size());
    }
}
