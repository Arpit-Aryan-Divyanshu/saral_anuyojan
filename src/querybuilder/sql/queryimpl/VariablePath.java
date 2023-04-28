
package org.ehrbase.aql.sql.queryimpl;

import java.util.List;
import org.ehrbase.aql.sql.queryimpl.value_field.NodePredicate;
import org.ehrbase.ehr.util.LocatableHelper;

/**
 * Created by christian on 5/3/2018.
 */
public class VariablePath {

    String path;

    public VariablePath(String path) {
        this.path = path;
    }

    public boolean hasPredicate() {

        if (path == null) return false;

        List<String> segments = LocatableHelper.dividePathIntoSegments(path);
        for (int i = 0; i < segments.size(); i++) {
            String nodeId = segments.get(i);
            if (new NodePredicate(nodeId).hasPredicate()) return true;
        }

        return false;
    }
}
