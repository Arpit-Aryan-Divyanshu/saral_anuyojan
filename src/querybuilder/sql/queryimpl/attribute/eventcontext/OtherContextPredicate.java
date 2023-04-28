
package org.ehrbase.aql.sql.queryimpl.attribute.eventcontext;

import org.ehrbase.webtemplate.parser.AqlPath;

/**
 * @author Christian Chevalley
 * @author Renaud Subiger
 * @since 1.0
 */
public class OtherContextPredicate {

    private final String path;

    public OtherContextPredicate(String path) {
        this.path = path;
    }

    public String adjustForQuery() {
        var aqlPath = AqlPath.parse(path);
        var aqlNodes = aqlPath.getNodes();

        for (int i = 0; i < aqlNodes.size(); i++) {
            if ("other_context".equals(aqlNodes.get(i).getName())) {
                aqlPath = aqlPath.replaceNode(i, aqlNodes.get(i).withAtCode(null));
            }
        }

        // Removes initial "/" if not present in original path
        var result = aqlPath.format(true);
        return path.startsWith("/") ? result : result.substring(1);
    }
}
