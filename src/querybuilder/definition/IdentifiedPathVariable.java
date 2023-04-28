
package org.ehrbase.aql.definition;

import org.ehrbase.aql.parser.AqlParser;


public class IdentifiedPathVariable {

    private AqlParser.IdentifiedPathContext identifiedPathContext;
    private AqlParser.SelectExprContext selectExprContext;
    private final PredicateDefinition predicateDefinition;
    private boolean isDistinct;

    public IdentifiedPathVariable(
            AqlParser.IdentifiedPathContext identifiedPathContext,
            AqlParser.SelectExprContext selectExprContext,
            boolean isDistinct,
            PredicateDefinition predicateDefinition) {
        this.identifiedPathContext = identifiedPathContext;
        this.selectExprContext = selectExprContext;
        this.isDistinct = isDistinct;
        this.predicateDefinition = predicateDefinition;
    }

    public VariableDefinition definition() {
        String identifier = identifiedPathContext.IDENTIFIER().getText();
        String path = null;
        if (identifiedPathContext.objectPath() != null
                && !identifiedPathContext.objectPath().isEmpty())
            path = identifiedPathContext.objectPath().getText();
        String alias = null;
        // get an alias if any
        if (selectExprContext.AS() != null) {
            alias = selectExprContext.IDENTIFIER().getText();
        }

        VariableDefinition variableDefinition;
        if (predicateDefinition == null)
            variableDefinition = new VariableDefinition(path, alias, identifier, isDistinct);
        else variableDefinition = new VariableDefinition(path, alias, identifier, isDistinct, predicateDefinition);
        return variableDefinition;
    }
}
