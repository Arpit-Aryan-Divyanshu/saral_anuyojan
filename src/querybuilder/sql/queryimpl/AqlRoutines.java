
package org.ehrbase.aql.sql.queryimpl;

import static org.ehrbase.aql.sql.queryimpl.QueryImplConstants.AQL_NODE_ITERATIVE_FUNCTION;
import static org.ehrbase.aql.sql.queryimpl.QueryImplConstants.AQL_NODE_NAME_PREDICATE_MARKER;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Configuration;
import org.jooq.Field;
import org.jooq.JSONB;
import org.jooq.impl.DSL;

public class AqlRoutines extends AqlDialects {

    private AqlRoutines() {
        super();
    }

    public static Field<JSONB> jsonArraySplitElements(Field<JSONB> jsonbVal) {
        return DSL.field(AQL_NODE_ITERATIVE_FUNCTION + "(" + jsonbVal + ")").cast(JSONB.class);
    }

    public static Field<JSONB> jsonArraySplitElements(Configuration configuration, Field<JSONB> jsonbVal) {
        isSupported(configuration);
        return DSL.field(AQL_NODE_ITERATIVE_FUNCTION + "(" + jsonbVal + ")").cast(JSONB.class);
    }

    public static Field<JSONB> jsonpathItem(Field<JSONB> jsonbVal, String[] elements) {
        return DSL.field("jsonb_extract_path(" + jsonbVal + "," + String.join(",", elements) + ")")
                .cast(JSONB.class);
    }

    public static Field<JSONB> jsonpathItem(Configuration configuration, Field<JSONB> jsonbVal, String[] elements) {
        isSupported(configuration);
        return DSL.field("jsonb_extract_path(" + jsonbVal + "," + String.join(",", elements) + ")")
                .cast(JSONB.class);
    }

    public static Field<JSONB> toJson(Configuration configuration, String expression) {
        isSupported(configuration);
        return DSL.field("to_json(" + expression + ")").cast(JSONB.class);
    }

    public static String jsonpathItemAsText(Field<JSONB> jsonbVal, String[] elements) {
        return "jsonb_extract_path_text(" + jsonbVal + "," + String.join(",", elements) + ")";
    }

    public static String jsonpathItemAsText(Configuration configuration, Field<JSONB> jsonbVal, String[] elements) {
        isSupported(configuration);
        return "jsonb_extract_path_text(" + jsonbVal + "," + String.join(",", elements) + ")";
    }

    public static String[] jsonpathParameters(String rawParameters) {
        String parametersFormatted = StringUtils.remove(StringUtils.remove(rawParameters, "'{"), "}'");
        return Arrays.stream(parametersFormatted.split(","))
                .map(s -> (s.startsWith("'") ? s.replace("'", "") : s))
                .map(s -> (!s.equals(AQL_NODE_NAME_PREDICATE_MARKER) ? "'" + s + "'" : s))
                .collect(Collectors.toList())
                .toArray(new String[] {});
    }
}
