
package org.ehrbase.aql.definition;

public class LateralVariable {

    private final String tableName;
    private final String variableId;

    public LateralVariable(String tableName, String variableId) {
        this.tableName = tableName;
        this.variableId = variableId;
    }

    public String alias() {
        return tableName + "." + variableId + " ";
    }
}
