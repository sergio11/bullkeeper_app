package sanchez.sanchez.sergio.masom_app.ui.support;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import net.grandcentrix.thirtyinch.TiPresenter;
import net.grandcentrix.thirtyinch.TiView;
import java.util.List;

/**
 * Support Fragment
 */
public abstract class SupportValidationFragment<P extends TiPresenter<V>, V extends TiView,
        H extends IBasicActivityHandler> extends SupportFragment<P, V, H>
    implements Validator.ValidationListener {

    protected Validator validator;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    /**
     * On Validation Failed
     * @param errors
     */
    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        onValidationFailed();
        for (ValidationError error : errors) {
            String message = error.getCollatedErrorMessage(getActivity());
            onFieldInvalid(error.getView().getId(), message);
        }
    }

    /**
     * On Validation Failed
     */
    protected abstract void onValidationFailed();

    /**
     * On Field Invalid
     * @param viewId
     * @param message
     */
    protected abstract void onFieldInvalid(final Integer viewId, final String message);

}
