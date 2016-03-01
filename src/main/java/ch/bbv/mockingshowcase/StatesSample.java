package ch.bbv.mockingshowcase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by MeisAnd1 on 04.02.2016.
 */
public final class StatesSample<T, V> {
    private final ExecutorService executor;
    private final Collection<Processor<T, V>> processors;

    public StatesSample(final ExecutorService executor,
                        final Collection<Processor<T, V>> processors) {
        this.executor = executor;
        this.processors = new ArrayList<>(processors);
    }

    public T doInExecutor(V parameter) throws Exception {
        final Future<T> future = executor.submit(() -> {
            // Aufrufe müssen sicher im Executor stattfinden
            // Dieser for-loop darf nicht ausserhalb des Callable sein.
            for (Processor<T, V> processor : processors) {
                final T result = processor.process(parameter);
                if (result != null) {
                    return result;
                }
            }
            throw new IllegalStateException();
        });

        while (true) {
            try {
                return future.get();
            } catch (InterruptedException e) {
                // operation nicht unterbrechbar
            } catch (Exception e) {
                throw e;
            }
        }
    }
}

