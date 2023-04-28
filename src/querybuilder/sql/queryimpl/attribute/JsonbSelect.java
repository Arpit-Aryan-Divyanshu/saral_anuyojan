
package org.ehrbase.aql.sql.queryimpl.attribute;

import java.util.List;

public class JsonbSelect {

    private final List<String> pathSegments;

    public JsonbSelect(List<String> pathSegments) {
        this.pathSegments = pathSegments;
    }

    public String field() {
        return "'{" + String.join(",", pathSegments) + "}'";
    }
}
