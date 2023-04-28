package org.ehrbase.aql.containment;

import java.util.List;
import org.ehrbase.webtemplate.parser.NodeId;

/**
 * Definition of a 'simple' chained CONTAINS
 */
public class SimpleChainedCheck extends ContainsCheck {

    private ContainmentSet containmentSet;

    public SimpleChainedCheck(String symbol, ContainmentSet containmentSet) {
        super(symbol);
        this.containmentSet = containmentSet;
    }

    public List<NodeId> jsonPathNodeFilterExpression() {
        if (containmentSet == null || containmentSet.getContainmentList().isEmpty()) return null;

        return new JsonPathQueryBuilder(containmentSet.getContainmentList().asList()).assemble();
    }

    public String toString() {
        if (containmentSet == null) return "";
        this.checkExpression = containmentSet.getContainmentList().toString();
        return checkExpression;
    }

    public String getSymbol() {
        return label;
    }

    public ContainmentSet getContainmentSet() {
        return containmentSet;
    }
}
