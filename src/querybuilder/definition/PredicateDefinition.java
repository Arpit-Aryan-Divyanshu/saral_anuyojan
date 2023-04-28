
package org.ehrbase.aql.definition;

public class PredicateDefinition {

    private final String operand1;
    private final String operator;
    private final String operand2;

    public PredicateDefinition(String operand1, String operator, String operand2) {
        this.operand1 = operand1;
        this.operator = operator;
        this.operand2 = operand2;
    }

    public String getOperand1() {
        return operand1;
    }

    public String getOperator() {
        return operator;
    }

    public String getOperand2() {
        return operand2;
    }
}
