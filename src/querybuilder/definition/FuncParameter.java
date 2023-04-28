
package org.ehrbase.aql.definition;


public class FuncParameter {

    private FuncParameterType type;
    private String value;

    public FuncParameter(FuncParameterType type, String value) {
        this.type = type;
        this.value = value;
    }

    public FuncParameterType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public boolean isOperand() {
        return type.equals(FuncParameterType.OPERAND);
    }

    public boolean isIdentifier() {
        return type.equals(FuncParameterType.IDENTIFIER);
    }

    public boolean isVariable() {
        return type.equals(FuncParameterType.VARIABLE);
    }
}
