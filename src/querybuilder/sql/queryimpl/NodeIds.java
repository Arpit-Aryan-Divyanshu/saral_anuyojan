
package org.ehrbase.aql.sql.queryimpl;

import java.util.StringTokenizer;

public class NodeIds { // from Locatable

    private NodeIds() {}

    public static String toCamelCase(String underscoreSeparated) {
        if (!underscoreSeparated.contains("_")) {
            return underscoreSeparated;
        }
        StringTokenizer tokens = new StringTokenizer(underscoreSeparated, "_");
        StringBuilder buf = new StringBuilder();
        while (tokens.hasMoreTokens()) {
            String word = tokens.nextToken();
            if (buf.length() == 0) {
                buf.append(word);
            } else {
                buf.append(word.substring(0, 1).toUpperCase());
                buf.append(word.substring(1));
            }
        }
        return buf.toString();
    }
}
