package ch.bbv.mockingshowcase;

import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
@RunWith(JMockit.class)
public class SequenceSampleJMockitTest {
    @Injectable
    private Processor<TestT, TestV> processor;
    @Tested
    private SequenceSample<TestT, TestV> testSubject;

    @Test
    public void anyParam_doSequence_callsInCorrectOrder(@Mocked TestV parameter) {
        //when
        testSubject.doSequence(parameter);
        //then
        new VerificationsInOrder() {
            {
                parameter.prepare();
                processor.preProcess(parameter);
                processor.process(parameter);
                processor.cleanup();
            }
        };

    }
}