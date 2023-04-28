package org.ehrbase.aql.containment;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.MapUtils;

/**
 * wrap the result of a jsonpath query
 * This is required since the result is generally a Map of multiple objects
 */
public class JsonPathQueryResult implements Serializable {

    private final String templateId;
    private final Set<String> aqlPath;

    public JsonPathQueryResult(String templateId, Map<String, Object> objectMap) {
        this.templateId = templateId;

        if (!MapUtils.isEmpty(objectMap)) {
            aqlPath = (Set<String>) objectMap.get("aql_path");
        } else {
            aqlPath = null;
        }
    }

    public JsonPathQueryResult(String templateId, Set<String> aqlPath) {
        this.templateId = templateId;
        this.aqlPath = aqlPath;
    }

    public String getTemplateId() {
        return templateId;
    }

    public Set<String> getAqlPath() {
        return aqlPath;
    }
}
