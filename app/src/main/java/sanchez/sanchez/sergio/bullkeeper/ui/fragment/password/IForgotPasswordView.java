package sanchez.sanchez.sergio.bullkeeper.ui.fragment.password;

import java.util.LinkedHashMap;
import java.util.List;
import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;

/**
 * Forgot Password View
 */
public interface IForgotPasswordView extends ISupportView {

    /**
     * On Password Reset
     */
    void onPasswordReset();

    /**
     * On Validation Errors
     * @param errors
     */
    void onValidationErrors(final List<LinkedHashMap<String, String>> errors);

}
