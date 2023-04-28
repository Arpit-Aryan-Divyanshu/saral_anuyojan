package org.ehrbase.aql.containment;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * Compare if two ANTLR parsed tokens are equivalent.
 */
public class CommonTokenCompare {

    private final ParseTree token;

    public CommonTokenCompare(ParseTree token) {
        this.token = token;
    }

    public boolean isEquals(int value) {
        return (token instanceof TerminalNode
                && (token.getPayload()) instanceof CommonToken
                && ((CommonToken) (token.getPayload())).getType() == value);
    }
}
