package org.ehrbase.aql.compiler;

import java.util.List;
import java.util.Stack;

/**
 * utility class to check some validity factors of a WHERE expression (f.e. balanced parenthesis)
 */
class WhereClauseUtil {

    private List<Object> expression;
    private String unbalanced;

    WhereClauseUtil(List<Object> expression) {
        this.expression = expression;
    }

    /**
     * check if an expression block is balanced
     * A block is delimited by '[...]', '{...}' or '(...)'
     * @return true if the block is balanced, false otherwise
     */
    boolean isBalancedBlocks() {

        Stack<Object> stack = new Stack<>();

        for (Object item : expression) {
            if (item.toString().length() == 1 && item.toString().matches("\\(|}|\\[")) stack.push(item);
            else if (item.toString().length() == 1 && item.toString().matches("\\)|\\{|\\]")) {
                if (stack.empty()) return false;
                else if (!isBalanced(stack.pop(), item)) return false;
            }
        }

        return stack.empty();
    }

    private boolean isBalanced(Object fromStack, Object actual) {
        boolean result = false;
        if (actual.toString().equals(")")) result = fromStack.toString().equals("(");
        else if (actual.toString().equals("]")) result = fromStack.toString().equals("[");
        if (actual.toString().equals("}")) result = fromStack.toString().equals("{");

        if (!result) unbalanced = actual.toString();

        return result;
    }

    /**
     * return the balanced state of the last check
     * @return boolean
     */
    String getUnbalanced() {
        return unbalanced;
    }
}
