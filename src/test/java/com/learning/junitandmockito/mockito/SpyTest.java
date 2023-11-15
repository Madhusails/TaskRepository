package com.learning.junitandmockito.mockito;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SpyTest {
    @Test
    public void test()
    {
//        List arrayListMock=mock(ArrayList.class);
//        assertEquals(0,arrayListMock.size());
//        // Stubbing the behavior to return 5 for size
//        when(arrayListMock.size()).thenReturn(5);
//        arrayListMock.add("dummy");
//        assertEquals(5,arrayListMock.size());
        List arrayListSpy=spy(ArrayList.class);
        assertEquals(0,arrayListSpy.size());

        arrayListSpy.add("dummy");
        assertEquals(1,arrayListSpy.size());
        verify(arrayListSpy).add("dummy");
        verify(arrayListSpy,never()).clear();

    }
}
