package sanchez.sanchez.sergio.masom_app.ui.support;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import net.grandcentrix.thirtyinch.TiFragment;
import net.grandcentrix.thirtyinch.TiPresenter;
import net.grandcentrix.thirtyinch.TiView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;

/**
 * Support Fragment
 */
public abstract class SupportFragment<P extends TiPresenter<V>, V extends TiView,
        H extends IBasicActivityHandler> extends TiFragment<P, V> implements  ISupportView {


    /**
     * Activity Handler
     */
    protected H activityHandler;

    /**
     * UnBinder
     */
    private Unbinder unbinder;

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        try {
            activityHandler = (H) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement IIntroActivityHandler");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null)
            unbinder.unbind();
    }

    @Override
    public void showShortMessage(String message) {
        activityHandler.showShortMessage(message);
    }

    @Override
    public void showLongMessage(String message) {
        activityHandler.showLongMessage(message);
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }


    protected abstract void initializeInjector();



}
