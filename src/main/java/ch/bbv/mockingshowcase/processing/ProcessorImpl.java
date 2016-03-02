package ch.bbv.mockingshowcase.processing;

/**
 * Created by AndreasMeister on 02.03.2016.
 */
public class ProcessorImpl<T,V> implements Processor<T, V> {
    @Override
    public T process(V parameter) {
        return null;
    }

    @Override
    public void preProcess(V parameter) {

    }

    @Override
    public void cleanup() {

    }
}
