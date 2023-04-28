
package org.ehrbase.aql.sql.queryimpl;

import java.util.HashSet;
import java.util.Set;

public class MultiPath {

    public Set<String> asSet(String singlePath) {
        Set<String> pathSet = new HashSet<>();
        pathSet.add(singlePath);
        return pathSet;
    }
}
