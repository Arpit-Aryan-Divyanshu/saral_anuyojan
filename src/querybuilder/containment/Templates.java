package org.ehrbase.aql.containment;

import java.util.ArrayList;
import java.util.List;
import org.ehrbase.service.KnowledgeCacheService;
import org.ehrbase.webtemplate.parser.NodeId;

/**
 * Process jsonpath queries on WebTemplates
 */
public class Templates {

    private final KnowledgeCacheService knowledgeCache;

    public Templates(KnowledgeCacheService knowledgeCache) {
        this.knowledgeCache = knowledgeCache;
    }

    /**
     * build the results for a jsonpath query applied to all defined templates in the KnowledgeCacheService
     *
     * @param jsonQueryExpression
     * @return
     */
    public List<JsonPathQueryResult> resolve(List<NodeId> jsonQueryExpression) {
        if (jsonQueryExpression == null) return null;

        List<JsonPathQueryResult> jsonPathQueryResults = new ArrayList<>();
        // traverse the templates and identify the ones satisfying the query
        for (String templateId : knowledgeCache.getAllTemplateIds()) {
            JsonPathQueryResult result = resolveForTemplate(templateId, jsonQueryExpression);
            if (result != null) {
                jsonPathQueryResults.add(result);
            }
        }
        return jsonPathQueryResults;
    }

    /**
     * build the results for a jsonpath query applied to a defined templates in the KnowledgeCacheService
     *
     * @param templateId
     * @param jsonQueryExpression
     * @return
     */
    public JsonPathQueryResult resolveForTemplate(String templateId, List<NodeId> jsonQueryExpression) {

        /*
        Map<String, Object> results = new OptJsonPath(knowledgeCache).evaluate(templateId,jsonQueryExpression);
        JsonPathQueryResult jsonPathQueryResult = knowledgeCache.resolveForTemplate(templateId, jsonQueryExpression);

        if (results != null && !results.isEmpty()){
            return  new JsonPathQueryResult(templateId, results);
        }
        return null;

        */
        return knowledgeCache.resolveForTemplate(templateId, jsonQueryExpression);
    }

    /**
     * retrieve composition Node Id from template
     * @param templateId
     * @return
     */
    public String rootArchetypeNodeId(String templateId) {
        try {
            return knowledgeCache.getQueryOptMetaData(templateId).getTree().getNodeId();

        } catch (Exception e) {
            throw new IllegalStateException("Could not retrieve template meta data:" + e);
        }
    }
}
