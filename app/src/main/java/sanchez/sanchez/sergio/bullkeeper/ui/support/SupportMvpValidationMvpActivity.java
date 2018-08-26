package sanchez.sanchez.sergio.bullkeeper.ui.support;

import android.os.Bundle;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import net.grandcentrix.thirtyinch.TiPresenter;
import net.grandcentrix.thirtyinch.TiView;
import java.util.List;

/**
 * Support Validation Activity
 */
public abstract class SupportMvpValidationMvpActivity<T extends TiPresenter<E>, E extends TiView> extends SupportMvpActivity<T,E>
        implements Validator.ValidationListener{

    protected Validator validator;

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);
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
            String message = error.getCollatedErrorMessage(this);
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

    /**
     * On Vaildation Succeeded
     */
    @Override
    public void onValidationSucceeded() {
        // On Reset Errors
        onResetErrors();
    }

    /**
     * On Reset Errors
     */
    protected abstract void onResetErrors();


    /**
     * On Reset Fields
     */
    protected void onResetFields(){
        // Reset Errors
        onResetErrors();
    }

    /**
     * Validate
     */
    protected final void validate(){
        // On Reset Errors
        onResetErrors();
        // Validate
        validator.validate();
    }
}
