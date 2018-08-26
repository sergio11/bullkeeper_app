package sanchez.sanchez.sergio.bullkeeper.ui.fragment.mykids;

import android.support.annotation.NonNull;

import net.grandcentrix.thirtyinch.TiPresenter;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import sanchez.sanchez.sergio.domain.models.SonEntity;

/**
 * Home Presenter
 */
public final class MyKidsFragmentPresenter extends TiPresenter<IMyKidsView> {

    @Inject
    public MyKidsFragmentPresenter(){}


    @Override
    protected void onAttachView(@NonNull IMyKidsView view) {
        super.onAttachView(view);

        final List<SonEntity> myKidsList = Arrays.asList(new SonEntity(), new SonEntity(),
                new SonEntity());

        view.onMyKidsLoaded(myKidsList);

    }
}
