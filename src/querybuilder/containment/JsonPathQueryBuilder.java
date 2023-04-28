package org.ehrbase.aql.containment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.iterators.ReverseListIterator;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.webtemplate.parser.NodeId;

/**
 * Build jsonpath expression matching containments
 */
public class JsonPathQueryBuilder {

    private Iterator<Object> reverseListIterator;

    public JsonPathQueryBuilder(List<Object> containmentList) {
        this.reverseListIterator = new ReverseListIterator(containmentList);
    }

    public List<NodeId> assemble() {
        List<NodeId> nodeIdList = new ArrayList<>();
        while (reverseListIterator.hasNext()) {
            Object containment = reverseListIterator.next();
            if (containment instanceof Containment) {
                String archetypeId = ((Containment) containment).getArchetypeId();
                NodeId nodeId = new NodeId(
                        ((Containment) containment).getClassName(),
                        StringUtils.isNotBlank(archetypeId) ? archetypeId : null);
                nodeIdList.add(nodeId);
            }
        }
        return nodeIdList;
    }
}
