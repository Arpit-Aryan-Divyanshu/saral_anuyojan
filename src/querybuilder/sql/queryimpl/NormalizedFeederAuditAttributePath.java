
package org.ehrbase.aql.sql.queryimpl;

import static org.ehrbase.aql.sql.queryimpl.QueryImplConstants.AQL_NODE_ITERATIVE_MARKER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NormalizedFeederAuditAttributePath extends NormalizedRmAttributePath {

    public NormalizedFeederAuditAttributePath(List<String> pathSegments) {
        super(pathSegments);
    }

    public List<String> transform() {
        List<String> resultingPaths = new ArrayList<>();

        if (pathSegments.size() == 1
                && pathSegments.get(0).contains(FEEDER_SYSTEM_ITEM_IDS)
                && !pathSegments.get(0).endsWith(FEEDER_SYSTEM_ITEM_IDS)) {
            resultingPaths.addAll(Arrays.asList(pathSegments.get(0).split(",")));
            int i = resultingPaths.indexOf(FEEDER_SYSTEM_ITEM_IDS);
            if (i >= 0) resultingPaths.add(i + 1, AQL_NODE_ITERATIVE_MARKER);
        }

        return resultingPaths;
    }
}
