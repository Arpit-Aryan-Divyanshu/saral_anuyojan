package org.ehrbase.aql.compiler;

import java.lang.reflect.Method;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.ehrbase.aql.parser.AqlLexer;
import org.ehrbase.aql.parser.AqlParser;

/**
 * Wrap the walkers for pass1 and pass2 as well as invoke the WHERE getQueryOptMetaData
 * <p>
 * The purpose of this class is to assemble all query parts from the AQL expression. The parts
 * are then passed to specific binders to translate and/or perform the query to
 * a backend.
 */
public class AqlExpression {

    private AqlParser aqlParser;
    private ParseTree parseTree;

    public AqlExpression parse(String query) {
        ANTLRInputStream antlrInputStream = new ANTLRInputStream(query);
        Lexer aqlLexer = new AqlLexer(antlrInputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(aqlLexer);
        this.aqlParser = new AqlParser(commonTokenStream);

        // define our own error listener (default one just display a message on System.err
        aqlLexer.removeErrorListeners();
        aqlLexer.addErrorListener(AqlErrorHandler.INSTANCE);
        aqlParser.removeErrorListeners();
        aqlParser.addErrorListener(AqlErrorHandler.INSTANCE);

        this.parseTree = aqlParser.query(); // begin parsing at query rule

        return this;
    }

    public AqlExpression parse(String expression, String ruleName) {
        ANTLRInputStream antlrInputStream = new ANTLRInputStream(expression);
        Lexer aqlLexer = new AqlLexer(antlrInputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(aqlLexer);
        this.aqlParser = new AqlParser(commonTokenStream);

        // define our own error listener (default one just display a message on System.err
        aqlLexer.removeErrorListeners();
        aqlLexer.addErrorListener(AqlErrorHandlerNoRecovery.INSTANCE);
        aqlParser.removeErrorListeners();
        aqlParser.addErrorListener(AqlErrorHandlerNoRecovery.INSTANCE);

        try {
            Method ruleMethod = aqlParser.getClass().getMethod(ruleName);

            this.parseTree = (ParseTree) ruleMethod.invoke(aqlParser); // begin parsing at query rule
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getCause().getMessage());
        }

        return this;
    }

    public String dump() {
        if (parseTree != null && aqlParser != null) return parseTree.toStringTree(aqlParser);
        else return "**not initialized**";
    }

    public ParseTree getParseTree() {
        if (parseTree == null)
            throw new IllegalStateException(
                    "Parse tree is not initialized, use parse() method before calling this method");
        return parseTree;
    }
}
