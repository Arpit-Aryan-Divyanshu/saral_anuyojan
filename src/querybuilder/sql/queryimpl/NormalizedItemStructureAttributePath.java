
package org.ehrbase.aql.sql.queryimpl;

import java.util.ArrayList;
import java.util.List;

public class NormalizedItemStructureAttributePath extends NormalizedRmAttributePath {

    public NormalizedItemStructureAttributePath(List<String> pathSegments) {
        super(pathSegments);
    }

    @Override
    public List<String> transformStartingAt(int fromIndex) {
        List<String> resultingPaths = new ArrayList<>();

        resultingPaths.addAll(pathSegments);
        for (int i = fromIndex; i < pathSegments.size(); i++) {
            String segment = pathSegments.get(i);

            resultingPaths.set(i, segment.replaceFirst("/", ""));

            if (segment.contains(OTHER_CONTEXT) || segment.contains(OTHER_DETAILS))
                break; // keep the rest of the path segments unchanged.
        }

        return resultingPaths;
    }
}
