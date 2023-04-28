
package org.ehrbase.aql.sql.queryimpl;

import java.util.List;
import org.ehrbase.aql.sql.queryimpl.value_field.NodePredicate;

/**
 * Created by christian on 5/9/2018.
 */
public class NodeNameValuePredicate {

    NodePredicate nodePredicate;

    public NodeNameValuePredicate(NodePredicate nodePredicate) {
        this.nodePredicate = nodePredicate;
    }

    public List<String> path(List<String> jqueryPath, String nodeId) {
        // do the formatting to allow name/value node predicate processing
        String predicate = nodePredicate.predicate();
        jqueryPath.add(new NodePredicate(nodeId).removeNameValuePredicate());
        // encode it to prepare for plpgsql function call: marker followed by the name/value predicate
        jqueryPath.add(QueryImplConstants.AQL_NODE_NAME_PREDICATE_MARKER);
        jqueryPath.add(predicate);

        return jqueryPath;
    }
}
