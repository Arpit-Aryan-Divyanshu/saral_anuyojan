package org.ehrbase.aql.containment;

import org.ehrbase.aql.parser.AqlParser;

public class SimpleClassExpressionIdentifier {

    AqlParser.SimpleClassExprContext simpleClassExprContext;

    public SimpleClassExpressionIdentifier(AqlParser.SimpleClassExprContext simpleClassExprContext) {
        this.simpleClassExprContext = simpleClassExprContext;
    }

    public String resolve() {
        String symbol;

        if (simpleClassExprContext.IDENTIFIER().isEmpty())
            throw new IllegalArgumentException("Void SimpleClassExpression:" + simpleClassExprContext.getText());
        else if (simpleClassExprContext.IDENTIFIER(1) != null)
            symbol = simpleClassExprContext.IDENTIFIER(1).getSymbol().getText();
        else
            symbol = new AnonymousSymbol()
                    .generate(simpleClassExprContext
                            .IDENTIFIER(0)
                            .getSymbol()
                            .getText()
                            .toUpperCase());

        return symbol;
    }
}
