package sanchez.sanchez.sergio.data.net.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Birthday Deserializer
 */
public final class BirthdayDeserializer extends JsonDeserializer<Date> {

    private final String dateFormat;

    public BirthdayDeserializer(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException {

        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        try {
            String date = jsonparser.getText();
            return format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
