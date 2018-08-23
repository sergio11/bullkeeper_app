package sanchez.sanchez.sergio.masom_app.ui.fragment.relations;

import android.support.annotation.NonNull;
import net.grandcentrix.thirtyinch.TiPresenter;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import sanchez.sanchez.sergio.domain.models.SocialMediaFriendEntity;

/**
 * Kid Relation Fragment Presenter
 */
public final class KidRelationFragmentPresenter extends TiPresenter<IKidRelationsFragmentView> {

    @Inject
    public KidRelationFragmentPresenter(){}


    @Override
    protected void onAttachView(@NonNull IKidRelationsFragmentView view) {
        super.onAttachView(view);

        final List<SocialMediaFriendEntity> socialMediaFriendEntities = Arrays.asList(new SocialMediaFriendEntity(),
                new SocialMediaFriendEntity(), new SocialMediaFriendEntity(), new SocialMediaFriendEntity(),
                new SocialMediaFriendEntity(), new SocialMediaFriendEntity());

        view.onKidRelationsLoaded(socialMediaFriendEntities);

    }
}
