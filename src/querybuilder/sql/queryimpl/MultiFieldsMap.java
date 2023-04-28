
package org.ehrbase.aql.sql.queryimpl;

import java.util.*;
import java.util.stream.Collectors;

public class MultiFieldsMap {

    protected Map<String, MultiFields> multiFieldsListAsMap;

    public MultiFieldsMap(List<MultiFields> multiFieldsList) {
        this.multiFieldsListAsMap = toMap(multiFieldsList);
    }

    public MultiFieldsMap() {}

    private Map<String, MultiFields> toMap(List<MultiFields> multiFieldsList) {

        Map<String, MultiFields> multiMap = new LinkedHashMap<>(); // preserve order of insertion

        for (MultiFields multiFields : multiFieldsList) {
            if (!multiFields.isEmpty())
                multiMap.put(
                        variableIdentifierPath(
                                multiFields.getVariableDefinition().getIdentifier(),
                                multiFields.getVariableDefinition().getPath()),
                        multiFields);
        }
        return multiMap;
    }

    public MultiFields get(String identifierPath) {
        return multiFieldsListAsMap.get(identifierPath);
    }

    public MultiFields get(String variableIdentifier, String variablePath) {
        // seek for multifields where the variable matches identifier and path
        List<MultiFields> result = multiFieldsListAsMap.values().stream()
                .filter(v -> v.getVariableDefinition().getIdentifier().equals(variableIdentifier)
                        && v.getVariableDefinition().getPath() != null
                        && v.getVariableDefinition().getPath().equals(variablePath))
                .collect(Collectors.toList());
        if (result.isEmpty()) return null;
        else return result.get(0); // by construction, the result is unique
    }

    private String variableIdentifierPath(String variableIdentifier, String variablePath) {
        return variableIdentifier + "::" + variablePath;
    }

    public Iterator<MultiFields> multiFieldsIterator() {
        return multiFieldsListAsMap.values().iterator();
    }

    /**
     * return the upper limit of all paths in the map
     */
    public int upperPathBoundary() {
        int upperbound = 0;

        for (MultiFields multiFields : multiFieldsListAsMap.values()) {
            if (multiFields.fieldsSize() > upperbound) upperbound = multiFields.fieldsSize();
        }
        return upperbound;
    }
}
