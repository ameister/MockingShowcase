package ch.bbv.mockingshowcase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class UnorderedSequenceSampleMockitoTest {

    @Mock
    Processor<TestT, TestV> processorMock;
    @InjectMocks
    UnorderedSequenceSample<TestT, TestV> testSubject;

    @Test
    public void anyParam_doUnordered_callsInCorrectOrder() throws Exception {
        //given
        final List<TestV> mockedParameters = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mockedParameters.add(mock(TestV.class));
        }
        //when
        testSubject.doUnordered(mockedParameters);
        //then
        for (TestV mockedParameter : mockedParameters) {
            InOrder inOrder = inOrder(mockedParameter, processorMock);
            inOrder.verify(mockedParameter).prepare();
            inOrder.verify(processorMock).preProcess(mockedParameter);
            inOrder.verify(processorMock).process(mockedParameter);
            inOrder.verify(processorMock).cleanup();
        }
    }
}