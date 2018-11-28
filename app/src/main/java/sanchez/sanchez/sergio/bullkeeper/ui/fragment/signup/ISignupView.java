package sanchez.sanchez.sergio.bullkeeper.ui.fragment.signup;

import java.util.LinkedHashMap;
import java.util.List;

import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;

/**
 * Signup View
 */
public interface ISignupView extends ISupportView {

    /**
     * On Signup Success
     */
    void onSignupSuccess(final GuardianEntity guardianEntity);

    /**
     * On Validation Errors
     * @param errors
     */
    void onValidationErrors(final List<LinkedHashMap<String, String>> errors);
}
