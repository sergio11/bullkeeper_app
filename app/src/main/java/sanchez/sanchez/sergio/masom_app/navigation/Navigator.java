package sanchez.sanchez.sergio.masom_app.navigation;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

    private final Context context;

    @Inject
    public Navigator(final Context context) {
        this.context = context;
    }

}
