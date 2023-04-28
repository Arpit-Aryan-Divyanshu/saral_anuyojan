
package org.ehrbase.aql.sql.queryimpl;

import static org.ehrbase.aql.sql.queryimpl.EntryAttributeMapper.OTHER_PARTICIPATIONS;

import java.util.ArrayList;
import java.util.List;

public class NormalizedOtherParticipations extends NormalizedRmAttributePath {

    public NormalizedOtherParticipations(List<String> pathSegments) {
        super(pathSegments);
    }

    public List<String> transform() {
        List<String> resultingPaths = new ArrayList<>();

        if (pathSegments.size() >= 1
                && pathSegments.get(pathSegments.size() - 1).contains(OTHER_PARTICIPATIONS)) {
            String otherParticipationsField = pathSegments.get(pathSegments.size() - 1);
            List<String> otherParticipations = new ArrayList<>(List.of(otherParticipationsField.split(",")));
            resultingPaths.addAll(pathSegments.subList(0, pathSegments.size() - 2));
            resultingPaths.addAll(otherParticipations);
        }

        return resultingPaths;
    }
}
