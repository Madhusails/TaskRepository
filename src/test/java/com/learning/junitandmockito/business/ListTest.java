package com.learning.junitandmockito.business;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;  // Import is from Hamcrest
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {
    @Test
    public void letsMockListSizeMethod()
    {
        List listMock=mock(List.class);
        when(listMock.size()).thenReturn(2);
        assertEquals(2,listMock.size());
    }

    @Test
    public void letsMockListSize_ReturnMultipleValues()
    {
        List listMock=mock(List.class);
        when(listMock.size()).thenReturn(2).thenReturn(3);
        assertEquals(2,listMock.size());
        assertEquals(3,listMock.size());
        assertEquals(3,listMock.size());
    }
    @Test
    public void letsMockListGet()
    {
        List listMock=mock(List.class);
        //when(listMock.get(0)).thenReturn("udemy");
        when(listMock.get(anyInt())).thenReturn("udemy");
        assertEquals("udemy",listMock.get(0));
        assertEquals("udemy",listMock.get(1));
        assertEquals("udemy",listMock.get(2));
    }
    @Test
    public void letsMockListGet_usingBDD()
    {
        //Given
        List<String> listMock=mock(List.class);
        //when(listMock.get(0)).thenReturn("udemy");
        given(listMock.get(anyInt())).willReturn("udemy");
        //when
        String firstElement=listMock.get(0);
        //then
        assertThat(firstElement,is("udemy"));
    }
    @Test
    public void letsMockList_throwAnException()
    {
        List listMock=mock(List.class);
        //when(listMock.get(0)).thenReturn("udemy");
        when(listMock.get(anyInt())).thenThrow(new RuntimeException("something is wrong"));
        assertThrows(RuntimeException.class, () -> {
            listMock.get(0);
        });
    }
}
