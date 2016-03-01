package ch.bbv.mockingshowcase;

import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
@RunWith(JMockit.class)
public class UnorderedSequenceSampleJMockitTest {

    @Injectable
    Processor<TestT, TestV> processorMock;
    @Tested
    UnorderedSequenceSample<TestT, TestV> testSubject;

    @Test
    public void anyParam_doUnordered_callsInCorrectOrder(@Mocked TestV paramMock1, @Mocked TestV paramMock2, @Mocked TestV paramMock3) throws Exception {
        //given
        final List<TestV> mockedParameters = Arrays.asList(paramMock1, paramMock2, paramMock3);
        //when
        testSubject.doUnordered(mockedParameters);
        //then
        // Überprüfen, dass auf alle Parameter die Aufrufe gemacht werden.
        new VerificationsInOrder() {
            {
                paramMock2.prepare();
                processorMock.preProcess(paramMock2);
                processorMock.process(paramMock2);
                processorMock.cleanup();
            }
        };
    }

    @Test
    public void anyParam_doUnordered_callsOnAllParam(@Mocked TestV paramMock1, @Mocked TestV paramMock2, @Mocked TestV paramMock3) throws Exception {
        //given
        final List<TestV> mockedParameters = Arrays.asList(paramMock1, paramMock2, paramMock3);
        //when
        testSubject.doUnordered(mockedParameters);
        //then
        //Verifications in einem for-Loop sind nicht m�glich, deshalb hier explizit
        new Verifications() {
            {
                paramMock2.prepare();
                processorMock.preProcess(paramMock2);
                processorMock.process(paramMock2);
                paramMock3.prepare();
                processorMock.preProcess(paramMock3);
                processorMock.process(paramMock3);
                paramMock1.prepare();
                processorMock.preProcess(paramMock1);
                processorMock.process(paramMock1);
                processorMock.cleanup();
            }
        };
    }
}