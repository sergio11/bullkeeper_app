package sanchez.sanchez.sergio.data.net.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;


/**
 * Joda Local Time Deserializer
 */
public class JodaLocalTimeDeserializer extends JsonDeserializer<LocalTime> {

    /**
     * Date Time Formatter
     */
    private DateTimeFormatter fmt;

    public JodaLocalTimeDeserializer(final String jodaTimeFormat) {
        this.fmt =  DateTimeFormat.forPattern(jodaTimeFormat);
    }

    @Override
    public LocalTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String textToParse = jp.getText().trim();
        LocalTime localTime = null;
        try {
            localTime = LocalTime.parse(textToParse, fmt);
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
        return localTime;
    }
}
