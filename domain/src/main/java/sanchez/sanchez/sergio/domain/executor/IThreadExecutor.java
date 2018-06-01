package sanchez.sanchez.sergio.domain.executor;

import java.util.concurrent.Executor;

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the
 * {@link sanchez.sanchez.sergio.domain.interactor.UseCase} out of the UI thread.
 */
public interface IThreadExecutor extends Executor {}
