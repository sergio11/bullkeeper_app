package sanchez.sanchez.sergio.domain.utils;

import java.util.Locale;

/**
 * App Utils Interface
 */
public interface IAppUtils {

    /**
     * Get Current Locale
     * @return
     */
    Locale getCurrentLocale();

    /**
     * Is Valid String
     * @return
     */
    Boolean isValidString(final String text);

}
