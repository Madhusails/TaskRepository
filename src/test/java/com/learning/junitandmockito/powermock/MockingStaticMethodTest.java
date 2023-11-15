package com.learning.junitandmockito.powermock;

import com.learning.junitandmockito.business.TodoBusinessImpl;
import com.learning.junitandmockito.data.api.TodoService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilityClass.class)
public class MockingStaticMethodTest {


    @Mock
    Dependency dependency;

    @InjectMocks
    SystemUnderTest systemUnderTest;


    @Test
    public void testRetrieveTodosRelatedToSpring_usingAMock() {
        List<Integer> stats = Arrays.asList(1,2,3);
        when(dependency.retrieveAllStats()).thenReturn(stats);
        PowerMockito.mockStatic(UtilityClass.class);
        when(UtilityClass.staticMethod(6)).thenReturn(150);
        int result=systemUnderTest.methodCallingAStaticMethod();
        assertEquals(150,result);
        PowerMockito.verifyStatic();
        UtilityClass.staticMethod(6);
    }


}
