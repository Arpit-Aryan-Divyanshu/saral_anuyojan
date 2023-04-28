package org.ehrbase.aql.containment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.ehrbase.service.KnowledgeCacheService;
import org.ehrbase.webtemplate.parser.NodeId;

/**
 * Convenience class to perform specific Containment operations related to containment path resolution
 */
public class Containments {

    private Set<Object> containmentSet;
    private KnowledgeCacheService knowledgeCacheService;

    public Containments(KnowledgeCacheService knowledgeCacheService, ContainmentSet containmentSet) {
        this.knowledgeCacheService = knowledgeCacheService;
        this.containmentSet = containmentSet.getContainmentList();
    }

    public boolean hasUnresolvedContainment(String templateId) {
        for (Object containment : containmentSet) {

            if (containment instanceof Containment && ((Containment) containment).getPath(templateId) == null)
                return true;
        }
        return false;
    }

    public void resolveContainers(String templateId) {

        // traverse the list from the last containment and resolve the ones with path
        List<Object> containmentList = new ArrayList<>();
        containmentList.addAll(containmentSet);

        for (int i = 0; i < containmentList.size(); i++) {
            if (containmentList.get(i) instanceof Containment) {
                Containment containment = (Containment) containmentList.get(i);

                if (containment.getClassName().equals("COMPOSITION") && containment.getArchetypeId() == null) {
                    continue;
                }

                if (containment.getPath(templateId) == null) {
                    List sublist = containmentList.subList(i, containmentList.size());
                    // build the jsonpath expression up to this containment
                    List<NodeId> jsonQuery = new JsonPathQueryBuilder(sublist).assemble();
                    // get the path for this template
                    JsonPathQueryResult jsonPathQueryResult =
                            new Templates(knowledgeCacheService).resolveForTemplate(templateId, jsonQuery);
                    if (jsonPathQueryResult != null) {
                        containment.setPath(templateId, jsonPathQueryResult.getAqlPath());
                    }
                }
            }
        }
    }
}
