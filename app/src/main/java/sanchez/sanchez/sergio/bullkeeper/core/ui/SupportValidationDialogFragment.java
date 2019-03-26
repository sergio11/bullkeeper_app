package sanchez.sanchez.sergio.bullkeeper.core.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

/**
 * Support Validation Dialog Fragment
 **/
public abstract class SupportValidationDialogFragment extends SupportDialogFragment
        implements Validator.ValidationListener {

    /**
     * Validator
     */
    protected Validator validator;

    /**
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        validator = new Validator(this);
        validator.setValidationListener(this);
    }


    /**
     * On Validation Succeeded
     */
    @Override
    public void onValidationSucceeded() {
        // On Reset Errors
        onResetErrors();
    }

    /**
     * On Validation Failed
     * @param errors
     */
    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        onValidationFailed();
        for (ValidationError error : errors) {
            String message = error.getCollatedErrorMessage(getContext());
            onFieldInvalid(error.getView().getId(), message);
        }
    }

    /**
     * On Reset Errors
     */
    protected abstract void onResetErrors();


    /**
     * On Reset Fields
     */
    protected void onResetFields(){ onResetErrors(); }

    /**
     * Validate
     */
    protected final void validate(){
        // On Reset Errors
        onResetErrors();
        // Validate
        validator.validate();
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
