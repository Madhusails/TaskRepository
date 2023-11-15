package com.learning.junitandmockito.business;

import com.learning.junitandmockito.data.api.TodoService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;



public class TodoBusinessImplMockitoInjectMockTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    TodoService todoService;

    @InjectMocks
    TodoBusinessImpl todoBusinessImpl;
    @Captor
    ArgumentCaptor<String> argumentCaptor;

    @Test
    public void usingMockito() {
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        when(todoService.retrieveTodos("dummy")).thenReturn(allTodos);
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("dummy");
        assertEquals(2, todos.size());
    }

    @Test
    public void usingMockitoEmptyList() {
        List<String> allTodos = Arrays.asList();
        when(todoService.retrieveTodos("dummy")).thenReturn(allTodos);
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("dummy");
        assertEquals(0, todos.size());
    }
    @Test
    public void testUsingBDD() {
        //Given
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        given(todoService.retrieveTodos("dummy")).willReturn(allTodos);
        //When
        List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("dummy");
        //Then
        assertThat(todos.size(), is(2));
    }
    @Test
    public void testDeleteUsingBDD() {
        //Given
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        given(todoService.retrieveTodos("dummy")).willReturn(allTodos);
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
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        Mockito.when(todoService.retrieveTodos("dummy")).thenReturn(allTodos);
        todoBusinessImpl.deleteTodosRelatedToSpring("dummy");
        Mockito.verify(todoService).deleteTodo(argumentCaptor.capture());

        assertEquals("Learn to Dance", argumentCaptor.getValue());
    }


}
