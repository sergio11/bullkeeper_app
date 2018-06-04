package sanchez.sanchez.sergio.masom_app;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;

/**
 * MainThread (UI Thread) implementation based on a {@link Scheduler}
 * which will execute actions on the Android UI thread
 *
 */
@Singleton
public class UIThread implements IPostExecutionThread {

    @Inject
    UIThread() {}

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
