package com.learning.junitandmockito.business;

import com.learning.junitandmockito.business.TodoBusinessImpl;
import com.learning.junitandmockito.data.api.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;  // Import is from Hamcrest
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;


public class TodoBusinessImplMockTest {
    @Test
    public void usingMockito() {
        TodoService todoService = mock(TodoService.class);
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        when(todoService.retrieveTodos("dummy")).thenReturn(allTodos);
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("dummy");
        assertEquals(2, todos.size());
    }

    @Test
    public void usingMockitoEmptyList() {
        TodoService todoService = mock(TodoService.class);
        List<String> allTodos = Arrays.asList();
        when(todoService.retrieveTodos("dummy")).thenReturn(allTodos);
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("dummy");
        assertEquals(0, todos.size());
    }
    @Test
    public void testUsingBDD() {
        //Given
        TodoService todoService = mock(TodoService.class);
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        given(todoService.retrieveTodos("dummy")).willReturn(allTodos);
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        //When
        List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("dummy");
        //Then
        assertThat(todos.size(), is(2));
    }
    @Test
    public void testDeleteUsingBDD() {
        //Given
        TodoService todoService = mock(TodoService.class);
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        given(todoService.retrieveTodos("dummy")).willReturn(allTodos);
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        //When
        todoBusinessImpl.deleteTodosRelatedToSpring("dummy");
        //Then
        //to call a method only once
        //verify(todoService,times(1)).deleteTodo("Learn to Dance");
        verify(todoService,atLeast(1)).deleteTodo("Learn to Dance");
        //then(todoService).should().deleteTodo("Learn Spring");
        //to verify whether a method is called
        //verify(todoService).deleteTodo("Learn Spring");

        //to verify whether a method is not called
        verify(todoService,never()).deleteTodo("Learn Spring MVC");

    }
    @Test
    public void captureArgument() {
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor
                .forClass(String.class);

        TodoService todoService = mock(TodoService.class);

        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        Mockito.when(todoService.retrieveTodos("dummy")).thenReturn(allTodos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        todoBusinessImpl.deleteTodosRelatedToSpring("dummy");
        Mockito.verify(todoService).deleteTodo(argumentCaptor.capture());

        assertEquals("Learn to Dance", argumentCaptor.getValue());
    }


}
