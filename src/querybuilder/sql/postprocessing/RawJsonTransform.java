
package org.ehrbase.aql.sql.postprocessing;

import com.google.gson.JsonElement;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonReader;
import org.ehrbase.serialisation.dbencoding.rawjson.LightRawJsonEncoder;
import org.jooq.Field;
import org.jooq.JSONB;
import org.jooq.Record;
import org.jooq.Result;


@SuppressWarnings({"unchecked", "java:S3776"})
public class RawJsonTransform implements IRawJsonTransform {

    public static final String TEMPLATE_ID = "_TEMPLATE_ID";
    public static final String ARRAY_MARKER = "$array$";

    private RawJsonTransform() {}

    public static void toRawJson(Result<Record> result) {

        if (result.isEmpty()) return;

        for (Record record : result) {

            for (Field field : record.fields()) {
                // get associated value
                if (record.getValue(field) instanceof String || record.getValue(field) instanceof JSONB) {
                    String value = record.getValue(field).toString();
                    String jsonbOrigin = null;
                    if (value.startsWith("[")) {
                        // check if this is a valid array
                        JsonReader jsonReader = Json.createReader(new StringReader(value));
                        try {
                            jsonReader.readArray();
                            jsonbOrigin = "{\"$array$\":" + value + "}";
                        } catch (JsonException e) {
                            // not a json array, do nothing
                        } finally {
                            jsonReader.close();
                        }
                    } else if (value.startsWith("{")) {
                        JsonReader jsonReader = Json.createReader(new StringReader(value));
                        try {
                            jsonReader.readObject();
                            jsonbOrigin = value;
                        } catch (JsonException e) {
                            // not a json object, do nothing
                        } finally {
                            jsonReader.close();
                        }
                    }
                    // apply the transformation
                    if (jsonbOrigin != null) {
                        JsonElement jsonElement = new LightRawJsonEncoder(jsonbOrigin).encodeContentAsJson(null);
                        if (jsonElement.getAsJsonObject().has(ARRAY_MARKER)) {
                            jsonElement = jsonElement.getAsJsonObject().getAsJsonArray(ARRAY_MARKER);
                        }
                        record.setValue(field, jsonElement);
                    }
                }
            }
        }
    }
}
