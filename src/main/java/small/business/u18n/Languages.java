package small.business.u18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author ihor
 */
public final class Languages {

    private final static Locale DEFAULT_LOCALE = new Locale("ua");
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("small.business.u18n.Language", DEFAULT_LOCALE);

    static public String getTranslatedText(String key) {
        return resourceBundle.getString(key);
    }
}