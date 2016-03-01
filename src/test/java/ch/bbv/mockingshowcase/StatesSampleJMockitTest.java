package ch.bbv.mockingshowcase;

import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

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
    public void someProcessors_doInExecutor_processCalled(@Mocked Future futureMock, @Mocked ExecutorService executor, @Mocked Processor<TestT, TestV> processor1, @Mocked Processor<TestT, TestV> processor2, @Mocked TestV parameter, @Mocked TestT processorResult) throws Exception {
        final StatesSample<TestT, TestV> testSubject = new StatesSample<>(executor, Arrays.asList(processor1, processor2));
        //given
        new Expectations() {
            {
                processor1.process(parameter);
                returns(null);
                processor2.process(parameter);
                returns(processorResult);
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
        List<Processor<TestT, TestV>> processors = Arrays.asList(processor2, processor1);
        for (Processor<TestT, TestV> processor : processors) {
            //Verifications in einer for-Schleife gehen, aber es wird nicht �berpr�ft, ob processor1.process(parameter) vor processor2.process(parameter) aufgerufen wird.
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