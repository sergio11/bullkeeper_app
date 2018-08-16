package sanchez.sanchez.sergio.masom_app.ui.fragment.relations;

import android.support.annotation.NonNull;
import net.grandcentrix.thirtyinch.TiPresenter;
import javax.inject.Inject;

/**
 * Kid Relation Fragment Presenter
 */
public final class KidRelationFragmentPresenter extends TiPresenter<IKidRelationsFragmentView> {

    @Inject
    public KidRelationFragmentPresenter(){}


    @Override
    protected void onAttachView(@NonNull IKidRelationsFragmentView view) {
        super.onAttachView(view);

    }
}
