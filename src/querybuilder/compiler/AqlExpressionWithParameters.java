package org.ehrbase.aql.compiler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AqlExpressionWithParameters extends AqlExpression {

    public static final String PARAMETERS_KEY = "query-parameters";

    public AqlExpressionWithParameters parse(String query, Map<String, Object> parameterValues) {
        String query1 = substitute(query, parameterValues);
        super.parse(query1);
        return this;
    }

    /**
     * get the parameter values from a json expression in the format:
     *
     *   "query-parameters": {
     *     "ehr_id": "7d44b88c-4199-4bad-97dc-d78268e01398",
     *     "systolic_bp": 140
     *   }
     *
     * @param expression
     * @param jsonParameterMap
     * @return
     */
    @Override
    public AqlExpressionWithParameters parse(String expression, String jsonParameterMap) {

        // get the map from the json expression
        Gson gson = new GsonBuilder().create();

        Map<String, Object> parameterMap = gson.fromJson(jsonParameterMap, Map.class);

        // get the map from query-parameters
        if (!parameterMap.containsKey(PARAMETERS_KEY))
            throw new IllegalArgumentException("Json map does not contain " + PARAMETERS_KEY);

        parameterMap = (Map<String, Object>) parameterMap.get(PARAMETERS_KEY);

        return parse(expression, parameterMap);
    }

    /**
     * Substitute parameters in aql expression
     * A parameter symbol starts with a '$' and a combination of alphanumeric with '-' or '_'
     * If the parameter value is a string or a UUID it is single quoted.
     * @param query
     * @param parameterValues
     * @return
     */
    public String substitute(String query, Map<String, Object> parameterValues) {

        StringBuffer stringBuffer = new StringBuffer();

        // match a string starting with '$' and followed by a number of alphanumeric or '-' or '_'
        Matcher matcher = Pattern.compile("\\$([\\w|\\-|_|]+)").matcher(query);

        while (matcher.find()) {
            String variable = matcher.group();
            if (parameterValues.get(variable.substring(1)) == null)
                throw new IllegalArgumentException(
                        "Could not substitute parameter in AQL expression: '" + variable + "'");
            Object parameterValue = parameterValues.get(variable.substring(1));

            if (isSingleQuotedArgument(parameterValue)) parameterValue = "'" + parameterValue + "'";

            matcher.appendReplacement(stringBuffer, String.valueOf(parameterValue));
        }

        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    private boolean isSingleQuotedArgument(Object parameterValue) {
        return parameterValue instanceof UUID || parameterValue instanceof String;
    }
}
