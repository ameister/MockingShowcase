package ch.bbv.mockingshowcase.processing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class StatesSampleMockitoTest {

    @Mock
    private Processor<TestT, TestV> processor1, processor2;
    @Mock
    private ExecutorService executor;
    @Mock
    private TestV parameter;

    @Test
    public void someProcessors_doInExecutor_processCalled() throws Exception {

        //given
        given(processor2.process(parameter)).willReturn(mock(TestT.class));
        final StatesSample<TestT, TestV> testSubject = new StatesSample<>(executor, Arrays.asList(processor1, processor2));
        willAnswer(invocation -> {
            (invocation.getArgumentAt(0, Callable.class)).call();
            return mock(Future.class);
        }).given(executor).submit(any(Callable.class));
        //when
        testSubject.doInExecutor(parameter);
        //then
        then(executor).should().submit(any(Callable.class));
        then(processor1).should().process(parameter);
        then(processor2).should().process(parameter);
    }

    @Test
    public void someProcessors_doInExecutor_processNotCalledOutsideCallable() throws Exception {
        final StatesSample<TestT, TestV> testSubject = new StatesSample<>(executor, Arrays.asList(processor1, processor2));
        willReturn(mock(Future.class)).given(executor).submit(any(Callable.class));
        //when
        testSubject.doInExecutor(parameter);
        //then
        then(executor).should().submit(any(Callable.class));
        then(processor2).should(never()).process(parameter);
        then(processor1).should(never()).process(parameter);
    }
}