package sanchez.sanchez.sergio.data.net.deserializers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Multi Date Deserializer
 */
public final class MultiDateDeserializer extends JsonDeserializer<Date> {

    private final List<String> dateFormats;

    /**
     *
     * @param dateFormats
     */
    public MultiDateDeserializer(final List<String> dateFormats) {
        this.dateFormats = dateFormats;
    }

    /**
     *
     * @param jp
     * @param ctxt
     * @return
     * @throws IOException
     * @throws JsonProcessingException
     */
    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        final String date = node.textValue();

        for (final String dateFormat : dateFormats) {
            try {
                return new SimpleDateFormat(dateFormat).parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        throw new JsonParseException(jp, "Unparseable date: \"" + date + "\". Supported formats: " + dateFormats.toString());
    }
}
