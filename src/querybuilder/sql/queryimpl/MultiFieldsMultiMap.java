
package org.ehrbase.aql.sql.queryimpl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MultiFieldsMultiMap extends MultiFieldsMap {

    public MultiFieldsMultiMap(List<MultiFields> multiFieldsList) {
        this.multiFieldsListAsMap = toMultiMap(multiFieldsList);
    }

    private Map<String, MultiFields> toMultiMap(List<MultiFields> multiFieldsList) {

        Map<String, MultiFields> multiMap = new LinkedHashMap<>(); // preserve order of insertion

        for (MultiFields multiFields : multiFieldsList) {
            if (!multiFields.isEmpty()) multiMap.put(variableIdentifierPath(multiFields), multiFields);
        }
        return multiMap;
    }

    private String variableIdentifierPath(MultiFields multiFields) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(multiFields.getVariableDefinition().getIdentifier());
        stringBuilder.append("::");
        stringBuilder.append(multiFields.getVariableDefinition().getPath());
        if (multiFields.getVariableDefinition().getAlias() != null) {
            stringBuilder.append("::");
            stringBuilder.append(multiFields.getVariableDefinition().getAlias());
        } else if (multiFields.getVariableDefinition().getPredicateDefinition() != null
                && multiFields.getVariableDefinition().getPredicateDefinition().getOperand2() != null) {
            stringBuilder.append("::");
            stringBuilder.append(
                    multiFields.getVariableDefinition().getPredicateDefinition().getOperand2());
        }
        return stringBuilder.toString();
    }
}
