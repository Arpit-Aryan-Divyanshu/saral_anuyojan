
package org.ehrbase.aql.sql.queryimpl;

import java.util.List;

/**
 * Created by christian on 5/9/2018.
 */
public class SegmentedPath {

    List<String> segmentedPathExpression;

    public SegmentedPath(List<String> segmentedPathExpression) {
        this.segmentedPathExpression = segmentedPathExpression;
    }

    public String reduce() {

        StringBuilder stringBuilder = new StringBuilder();

        for (String segment : segmentedPathExpression) {

            if (segment.startsWith("/feeder_audit")) {
                stringBuilder.append("/feeder_audit"); // sub-field are undecidable including type in other_details
                break;
            }

            if (segment.startsWith("/composition")
                    || segment.startsWith("/value")
                    || (!segment.contains("[") && !segment.contains("]"))
                    || !segment.startsWith("/")) continue;

            stringBuilder.append(segment);
        }

        return stringBuilder.toString();
    }
}
