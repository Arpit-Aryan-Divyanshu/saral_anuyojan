package org.ehrbase.aql.containment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.util.List;
import java.util.Map;
import org.ehrbase.service.KnowledgeCacheService;

/**
 * prepare and perform jsonpath queries on WebTemplates
 */
public class OptJsonPath {

    private KnowledgeCacheService knowledgeCache;

    public OptJsonPath(KnowledgeCacheService knowledgeCache) {
        this.knowledgeCache = knowledgeCache;
    }

    private String toJson(Map<String, Object> map) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        return gson.toJson(map);
    }

    public Map<String, Object> jsonPathEval(String json, String jsonPathExpression) {
        DocumentContext jsonPathContext = JsonPath.parse(json);
        Object pathResult = jsonPathContext.read(JsonPath.compile(jsonPathExpression));

        if (pathResult instanceof List && !((List) pathResult).isEmpty())
            return (Map<String, Object>) ((List) pathResult).get(0);
        else if (pathResult instanceof Map && !((Map) pathResult).isEmpty()) return (Map<String, Object>) pathResult;
        else return null;
    }
}
