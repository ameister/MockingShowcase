package ch.bbv.mockingshowcase.processing;

import java.util.Collection;
import java.util.concurrent.Callable;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
public class ProcessorCallable<T,V> implements Callable<T> {

    private Collection<Processor<T, V>> processors;
    private V parameter;

    public ProcessorCallable(final Collection<Processor<T, V>> processors, V parameter) {
        this.processors = processors;
        this.parameter = parameter;
    }

    @Override
    public T call() throws Exception {
        for (Processor<T, V> processor : processors) {
            final T result = processor.process(parameter);
            if (result != null) {
                return result;
            }
        }
        throw new IllegalStateException();
    }
}
