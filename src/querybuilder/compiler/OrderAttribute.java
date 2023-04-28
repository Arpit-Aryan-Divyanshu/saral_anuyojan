package org.ehrbase.aql.compiler;

import org.ehrbase.aql.definition.I_VariableDefinition;

/**
 * Representation of the ORDER BY clause in an AQL query
 */
public class OrderAttribute {
    public enum OrderDirection {
        ASC,
        DESC
    }

    private OrderDirection direction;
    private I_VariableDefinition variableDefinition;

    public OrderAttribute(I_VariableDefinition variableDefinition) {
        this.variableDefinition = variableDefinition;
    }

    public void setDirection(OrderDirection direction) {
        this.direction = direction;
    }

    public OrderDirection getDirection() {
        return direction;
    }

    public I_VariableDefinition getVariableDefinition() {
        return variableDefinition;
    }
}
