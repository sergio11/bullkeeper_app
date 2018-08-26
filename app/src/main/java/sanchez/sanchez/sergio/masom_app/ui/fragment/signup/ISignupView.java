package sanchez.sanchez.sergio.masom_app.ui.fragment.signup;

import java.util.LinkedHashMap;
import java.util.List;

import sanchez.sanchez.sergio.domain.models.ParentEntity;
import sanchez.sanchez.sergio.masom_app.ui.support.ISupportView;

/**
 * Signup View
 */
public interface ISignupView extends ISupportView {

    /**
     * On Signup Success
     */
    void onSignupSuccess(final ParentEntity parentEntity);

    /**
     * On Validation Errors
     * @param errors
     */
    void onValidationErrors(final List<LinkedHashMap<String, String>> errors);
}
