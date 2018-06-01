package sanchez.sanchez.sergio.data.executor;

import android.support.annotation.NonNull;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;

import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;

/**
 * Decorated {@link java.util.concurrent.ThreadPoolExecutor}
 */
@Singleton
public class JobExecutor implements IThreadExecutor {

    private final static Integer CORE_POOL_SIZE = 3;
    private final static Integer MAXIMUM_POOL_SIZE = 5;
    private final static Integer KEEP_ALIVE_TIME = 10;

    private final ThreadPoolExecutor threadPoolExecutor;

    @Inject
    public JobExecutor() {
        this.threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new JobThreadFactory());
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        this.threadPoolExecutor.execute(runnable);
    }


    /**
     * Job Thread Factory
     */
    private static class JobThreadFactory implements ThreadFactory {
        private int counter = 0;
        @Override public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, "android_" + counter++);
        }
    }
}