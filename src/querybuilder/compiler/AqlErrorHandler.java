package org.ehrbase.aql.compiler;

import java.util.BitSet;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.ehrbase.aql.compiler.recovery.RecoverArchetypeId;

/**
 * Utility class to handle specific errors during parsing<br>
 * Allows to return more meaningful messages during AQL parsing
 */
public class AqlErrorHandler extends BaseErrorListener {

    public static final AqlErrorHandler INSTANCE = new AqlErrorHandler();
    public static final boolean REPORT_SYNTAX_ERRORS = true;

    @Override
    public void syntaxError(
            Recognizer<?, ?> recognizer,
            Object offendingSymbol,
            int line,
            int charPositionInLine,
            String msg,
            RecognitionException e) {
        if (!REPORT_SYNTAX_ERRORS) {
            return;
        }

        String sourceName = recognizer.getInputStream().getSourceName();
        if (!sourceName.isEmpty()) {
            sourceName = String.format("%s:%d:%d: ", sourceName, line, charPositionInLine);
        }

        if (e != null && new RecoverArchetypeId().isRecoverableArchetypeId(e.getCtx(), offendingSymbol)) {
            return; // ignore since it will be 'fixed' by the recognizer
        }

        throw new ParseCancellationException(
                "AQL Parse exception: " + (sourceName.isEmpty() ? "source:" + sourceName : "") + "line " + line
                        + ": char " + charPositionInLine + " " + msg);
    }

    @Override
    public void reportAmbiguity(
            Parser recognizer,
            DFA dfa,
            int startIndex,
            int stopIndex,
            boolean exact,
            BitSet ambigAlts,
            ATNConfigSet configs) {

        //        System.out.println("Ambiguous");
    }
}
