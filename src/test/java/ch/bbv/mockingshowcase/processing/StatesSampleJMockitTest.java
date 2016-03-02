package ch.bbv.mockingshowcase.processing;

import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
@RunWith(JMockit.class)
public class StatesSampleJMockitTest {

    @Test
    public void someProcessors_doInExecutor_processCalled(@Mocked Future<TestT> futureMock, @Mocked ExecutorService executor, @Mocked ProcessorImpl<TestT, TestV> processorPrototyp, @Mocked TestV parameter, @Mocked TestT processorResult) throws Exception {
        final List<Processor<TestT, TestV>> processorMocks = new ArrayList<>();
        final TestT[] results = new TestT[200];
        for (int i = 0; i < 200; i++) {
            //die hier erzeugten Instanzen sind auch Mocks
            processorMocks.add(new ProcessorImpl<>());
        }
        processorMocks.add(processorPrototyp);
        results[199] = processorResult;

        final StatesSample<TestT, TestV> testSubject = new StatesSample<>(executor, processorMocks);
        //given
        new Expectations() {
            {
                //wenn hier eine expectation agegeben wird gilt diese fuer alle Mocks
                processorPrototyp.process(parameter); returns(null, results);
                executor.submit(withInstanceOf(Callable.class));
                result = new Delegate() {
                    Future<TestT> delegate(Invocation invocation) throws Exception {
                        {
                            final Object[] arguments = invocation.getInvokedArguments();
                            Callable callable = (Callable) arguments[0];
                            callable.call();
                            return futureMock;
                        }
                    }
                };

            }
        };
        //when
        testSubject.doInExecutor(parameter);
        //then
        for (Processor<TestT, TestV> processor : processorMocks) {
            //Verifications in einer for-Schleife gehen, aber es wird nicht überprüft, ob processor1.process(parameter) vor processor2.process(parameter) aufgerufen wird.
            new VerificationsInOrder() {
                {
                    processor.process(parameter);
                }
            };
        }
    }

    @Test
    public void someProcessors_doInExecutor_processNotCalledOutsideCallable(@Mocked ExecutorService executor, @Mocked Processor<TestT, TestV> processor1, @Mocked Processor<TestT, TestV> processor2, @Mocked TestV parameter) throws Exception {
        final StatesSample<TestT, TestV> testSubject = new StatesSample<>(executor, Arrays.asList(processor1, processor2));
        //when
        testSubject.doInExecutor(parameter);
        //then
        new Verifications() {
            {
                executor.submit(withInstanceOf(Callable.class));
                processor1.process(parameter); times = 0;
                processor2.process(parameter); times = 0;
            }
        };
    }
}